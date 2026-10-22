#!/usr/bin/env bash
set -euo pipefail

IMAGE_TAG="${1:-enhsp25}"

docker build \
  --file Dockerfile \
  --tag "${IMAGE_TAG}" \
  "$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)"

echo "Built image: ${IMAGE_TAG}"
