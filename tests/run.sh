#!/usr/bin/env bash
set -euo pipefail

root_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$root_dir"

classes_dir="build/tests/classes"
logs_dir="build/tests/logs"
classpath="lib/runtime/*:lib/test-dependencies/*"
mkdir -p "$classes_dir"
mkdir -p "$logs_dir"
find "$classes_dir" -type f -delete
find "$logs_dir" -type f -delete

sources=()
while IFS= read -r source; do
  sources+=("$source")
done < <(find src tests/java -type f -name '*.java' | LC_ALL=C sort)
if ((${#sources[@]} == 0)); then
  echo 'No Java sources found.' >&2
  exit 1
fi
javac --release 16 -cp "$classpath" -d "$classes_dir" "${sources[@]}"

test_classes=()
while IFS= read -r source; do
  class_name="${source##*/}"
  test_classes+=("${class_name%.java}")
done < <(find tests/java -type f \( -name '*Test.java' -o -name 'planningTest.java' \) | LC_ALL=C sort)
if ((${#test_classes[@]} == 0)); then
  echo 'No JUnit test classes found.' >&2
  exit 1
fi

failed=0
for test_class in "${test_classes[@]}"; do
  log_file="$logs_dir/$test_class.log"
  if java -cp "$classes_dir:$classpath" org.junit.runner.JUnitCore "$test_class" >"$log_file" 2>&1; then
    printf 'PASS %s\n' "$test_class"
  else
    printf 'FAIL %s\n' "$test_class"
    tail -n 35 "$log_file"
    failed=1
  fi
done

exit "$failed"
