#!/usr/bin/env bash
set -euo pipefail

root_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
cd "$root_dir"

build_dir="build/compile/classes"
runtime_lib="lib/runtime"
cli_jar="enhsp25.jar"
gui_jar="enhsp25-gui.jar"

if [[ ! -d "$runtime_lib" ]] || ! compgen -G "$runtime_lib/*.jar" >/dev/null; then
  echo "No runtime dependencies found in $runtime_lib" >&2
  exit 1
fi

mkdir -p "$build_dir"
find "$build_dir" -type f -delete
find src -name '*.java' -print0 | xargs -0 javac --release 16 -cp "$runtime_lib/*" -d "$build_dir"

create_manifest() {
  local destination="$1"
  local main_class="$2"
  local class_path="Class-Path:"
  local jar_file

  for jar_file in "$runtime_lib"/*.jar; do
    [[ -f "$jar_file" ]] || continue
    class_path+=" lib/runtime/$(basename "$jar_file")"
  done

  {
    printf 'Manifest-Version: 1.0\nMain-Class: %s\n' "$main_class"
    while ((${#class_path} > 70)); do
      printf '%s\n' "${class_path:0:70}"
      class_path=" ${class_path:70}"
    done
    printf '%s\n\n' "$class_path"
  } > "$destination"
}

create_jar() {
  local jar_name="$1"
  local main_class="$2"
  local manifest="build/compile/manifest-$main_class.mf"
  create_manifest "$manifest" "$main_class"
  jar --create --file "$jar_name" --manifest "$manifest" -C "$build_dir" .
}

create_jar "$cli_jar" enhsp2.main
create_jar "$gui_jar" enhsp2.gui.PlanningWorkbench
printf 'Created %s and %s\n' "$cli_jar" "$gui_jar"
