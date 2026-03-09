#!/bin/bash

# Destination directory for compiled files and temporary files
output_directory="output"
tmp_extracted_jars="tmp_extracted_jars"

# Final output JAR files
gui_jar="enhsp25-gui.jar"
cli_jar="enhsp25.jar"

# Compile Java source files
echo "Compiling source files..."
javac --release 16 -d "$output_directory" -cp "jar_dependencies/*" $(find src -name '*.java')

# Create a temporary directory to extract JAR file dependencies
mkdir -p "$tmp_extracted_jars"

# Extract the contents of each JAR file dependency into the temporary directory
echo "Extracting JAR file dependencies..."
for jar_file in jar_dependencies/*.jar; do
    echo "Extracting $jar_file..."
    unzip -qq -o "$jar_file" -d "$tmp_extracted_jars"
done

create_manifest() {
    local manifest_file="$1"
    local main_class="$2"

    {
      echo "Manifest-Version: 1.0"
      echo "Main-Class: $main_class"

      # Generate the Class-Path field
      class_path="Class-Path: "
      for jar_file in jar_dependencies/*.jar; do
          jar_file_name=$(basename "$jar_file")
          class_path="$class_path jar_dependencies/$jar_file_name"
      done

      # Handle manifest line length (72 chars max per line)
      class_path_length=${#class_path}
      max_length=72
      while [ $class_path_length -gt $max_length ]; do
        echo "${class_path:0:$max_length}"
        class_path=" ${class_path:$max_length}"
        class_path_length=${#class_path}
      done
      echo "$class_path"
    } > "$manifest_file"
}

create_jar() {
    local jar_name="$1"
    local main_class="$2"
    local manifest_file="$3"

    create_manifest "$manifest_file" "$main_class"
    echo "Creating $jar_name (Main-Class: $main_class)..."
    jar cfm "$jar_name" "$manifest_file" -C "$output_directory" . -C "$tmp_extracted_jars" .
}

create_jar "$gui_jar" "enhsp2.gui.PlanningWorkbench" "MANIFEST_GUI.MF"
create_jar "$cli_jar" "enhsp2.main" "MANIFEST_CLI.MF"

# Cleanup
echo "Cleaning up..."
rm -rf "$tmp_extracted_jars"
rm -rf "$output_directory"
rm -f MANIFEST_GUI.MF MANIFEST_CLI.MF

echo "Operation completed. Created: $gui_jar and $cli_jar"
