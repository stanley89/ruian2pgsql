#!/bin/bash

ZIP_DIR='./zip'
GZ_DIR='./gz'


for f in $ZIP_DIR/*.zip
do
  echo "Processing $f file..."
  unzip "$f" -d $GZ_DIR
  gzip $GZ_DIR/*.xml
done

