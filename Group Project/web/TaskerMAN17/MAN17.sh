#!/bin/bash

echo "start"
find -name '*.php' -exec chmod 700 {} \;
echo "PHP file permissions set"
find -name '*.css' -exec chmod 644 {} \;
echo "CSS file permissions set"
echo "finish"

