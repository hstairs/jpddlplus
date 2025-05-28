#!/bin/bash

# Destination directory for compiled files and the final JAR file
output_directory="output"

# Compile Java source files
echo "Compiling source files..."
javac -d "$output_directory" -cp "jar_dependencies/*" $(find src -name '*.java')

# Create a temporary directory to extract JAR file dependencies
mkdir -p tmp_extracted_jars

# Extract the contents of each JAR file dependency into the temporary directory
echo "Extracting JAR file dependencies..."
for jar_file in jar_dependencies/*.jar; do
    echo "Extracting $jar_file..."
    unzip -qq -o "$jar_file" -d tmp_extracted_jars
done

# Create the main JAR file in the temporary directory using the specified manifest file
echo "Creating the main JAR file in the temporary directory..."
jar cf jpddlplus.jar -C "$output_directory" . -C tmp_extracted_jars .

# Temporary manifest file
temp_manifest="MANIFEST.MF"
{
  echo "Manifest-Version: 1.0"
  echo "Main-Class: main"
  
  # Generate the Class-Path field
  class_path="Class-Path: "
  for jar_file in jar_dependencies/*.jar; do
      jar_file_name=$(basename "$jar_file")
      class_path="$class_path jar_dependencies/$jar_file_name"
  done

  # Handling line length in the manifest
  class_path_length=${#class_path}
  max_length=72
  while [ $class_path_length -gt $max_length ]; do
    echo "${class_path:0:$max_length}"
    class_path=" ${class_path:$max_length}"
    class_path_length=${#class_path}
  done
  echo "$class_path"
} > "$temp_manifest"

# Update the JAR file with the new manifest
jar ufm jpddlplus.jar MANIFEST.MF

# Cleanup
echo "Cleaning up..."
rm -rf tmp_extracted_jars
rm -rf "$output_directory"
rm MANIFEST.MF

echo "Operation completed."
