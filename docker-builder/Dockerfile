FROM ubuntu:22.04

ARG JPDDLPLUS_REPO=https://github.com/hstairs/jpddlplus.git
ARG JPDDLPLUS_BRANCH=enhsp25-newtrigonometricfunction

RUN apt-get update && apt-get install -y --no-install-recommends \
    ca-certificates \
    git \
    unzip \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Clone the current default branch from GitHub and build using the project script.
RUN set -eux; \
    git clone --depth 1 --single-branch --branch "${JPDDLPLUS_BRANCH}" "${JPDDLPLUS_REPO}" /jpddlplus; \
    cd /jpddlplus; \
    bash ./compile.sh; \
    mkdir -p /jpddlplus-dist; \
    cp -r jar_dependencies /jpddlplus-dist/; \
    cp enhsp25.jar enhsp25-gui.jar /jpddlplus-dist/

# Optional runtime image for running Java applications in derived images.
RUN jlink \
    --no-header-files \
    --no-man-pages \
    --compress=2 \
    --strip-java-debug-attributes \
    --add-modules java.base \
    --output /java-runtime

RUN rm -rf /jpddlplus

ENTRYPOINT ["/java-runtime/bin/java", "-jar", "/jpddlplus-dist/enhsp25.jar"]
CMD ["-h"]

LABEL Author="Enrico Scala enricos83@gmail.com"
