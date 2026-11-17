#!/usr/bin/env python3
"""Run initial-state cut diagnostics, terminating each planner after its diagnostic."""
import argparse, concurrent.futures, json, subprocess, time
from pathlib import Path
p=argparse.ArgumentParser();p.add_argument('--root',type=Path,required=True);p.add_argument('--jar',type=Path,required=True);p.add_argument('--out',type=Path,required=True);p.add_argument('--timeout',type=int,default=300);p.add_argument('--workers',type=int,default=2);a=p.parse_args()
a.out.mkdir(parents=True,exist_ok=True)
tasks=[(d,f,mode) for d in sorted(a.root.iterdir()) if (d/'domain.pddl').exists() for f in sorted((d/'instances').glob('*.pddl')) for mode in ('base','numfract')]
def run(t):
 d,f,mode=t;target=a.out/d.name/mode;target.mkdir(parents=True,exist_ok=True);prefix=target/f.stem
 cmd=['java','-Xmx2g','-cp',str(a.jar.resolve())+':'+str(Path('lib/runtime').resolve())+'/*','enhsp2.main','-o',str(d/'domain.pddl'),'-f',str(f),'-s','wastar','-wh','1','-ties','larger_g','-h','cpzerocut','-floor','true','-crd','none','-red','no','-silent','-diagnostic_cut','-selzero',mode,'-timeout',str(a.timeout)]
 log=prefix.with_suffix('.log'); start=time.monotonic()
 with log.open('w') as out:
  proc=subprocess.Popen(cmd,stdout=out,stderr=subprocess.STDOUT,start_new_session=True)
  found=False
  while proc.poll() is None and time.monotonic()-start<a.timeout:
   time.sleep(.2)
   if 'Diagnostic cut (initial):' in log.read_text(errors='replace'):
    found=True;proc.terminate();break
  try: proc.wait(timeout=3)
  except subprocess.TimeoutExpired: proc.kill();proc.wait()
 record={'domain':d.name,'problem':f.name,'mode':mode,'command':cmd,'diagnostic':next((x for x in log.read_text(errors='replace').splitlines() if x.startswith('Diagnostic cut (initial):')),None),'exit_code':proc.returncode,'elapsed':time.monotonic()-start}
 prefix.with_suffix('.json').write_text(json.dumps(record,indent=2)+'\n');print(d.name,f.name,mode,'ok' if record['diagnostic'] else 'missing',flush=True)
with concurrent.futures.ThreadPoolExecutor(max_workers=a.workers) as pool:list(pool.map(run,tasks))
