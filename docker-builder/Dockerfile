FROM ubuntu:22.04

ARG JPDDLPLUS_REPO=https://github.com/hstairs/jpddlplus.git
ARG JPDDLPLUS_BRANCH=enhsp25-newtrigonometricfunction

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

RUN apt-get update && apt-get install -y --no-install-recommends \
    ca-certificates \
    git \
    unzip \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Clone the selected branch and build using the project script.
RUN set -eux; \
    git clone --depth 1 --single-branch --branch "${JPDDLPLUS_BRANCH}" "${JPDDLPLUS_REPO}" /jpddlplus; \
    cd /jpddlplus; \
    rm -f enhsp25.jar enhsp25-gui.jar; \
    mkdir -p output tmp_extracted_jars; \
    bash -e ./compile.sh; \
    test -f enhsp25.jar; \
    jar tf enhsp25.jar | grep -q '^enhsp2/main.class$'; \
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
