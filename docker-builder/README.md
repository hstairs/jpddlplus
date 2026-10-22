# Docker build

There is one Dockerfile at the repository root. It compiles the local checkout
in a build stage and copies the CLI JAR plus runtime libraries into a smaller
Java runtime image. The `build.sh` script is a convenience wrapper:

```bash
./docker-builder/build.sh enhsp25:local
docker run --rm enhsp25:local -help
```

The image contains the current worktree, including edits that have not been
committed. To run a problem from this checkout, mount the repository:

```bash
docker run --rm -v "$PWD:/work" -w /work enhsp25:local \
  -o examples/pddl2_1/counters/domain.pddl \
  -f examples/pddl2_1/counters/instance_4.pddl
```
