#!/bin/bash

if [ $# -ne 2 ]; then
    echo "Error: no domain/project info"
    echo "Usage: create_project.bat <domain> <project>"
    echo "		e.g.: create_project SolutionDevelopment SampleProject"
    read -n1 -r -p "Press any key to continue..." key
    exit 1
fi

if [ ! -d $1 ]; then
    mkdir $1
fi
cd $1

if [ ! -d $2 ]; then
    mkdir $2
fi
cd $2

mkdir "features"
mkdir -p "src/main/java/com/macys/sdt/projects/$1/$2"
cd "src/main"
mkdir -p "resources/elements/website/mcom/pages"
mkdir -p "resources/elements/website/mcom/panels"
mkdir -p "resources/elements/website/bcom/pages"
mkdir -p "resources/elements/website/bcom/panels"
mkdir -p "resources/elements/MEW/mcom/pages"
mkdir -p "resources/elements/MEW/mcom/panels"
mkdir -p "resources/elements/MEW/bcom/pages"
mkdir -p "resources/elements/MEW/bcom/panels"
mkdir -p "resources/elements/responsive/mcom/pages"
mkdir -p "resources/elements/responsive/mcom/panels"
mkdir -p "resources/elements/responsive/bcom/pages"
mkdir -p "resources/elements/responsive/bcom/panels"
cd "java/com/macys/sdt/projects/$1/$2"
mkdir -p "actions/website/mcom/pages"
mkdir -p "actions/website/mcom/panels"
mkdir -p "actions/website/bcom/pages"
mkdir -p "actions/website/bcom/panels"
mkdir -p "actions/MEW/mcom/pages"
mkdir -p "actions/MEW/mcom/panels"
mkdir -p "actions/MEW/bcom/pages"
mkdir -p "actions/MEW/bcom/panels"
mkdir -p "actions/responsive/mcom/pages"
mkdir -p "actions/responsive/mcom/panels"
mkdir -p "actions/responsive/bcom/pages"
mkdir -p "actions/responsive/bcom/panels"
mkdir -p "steps/website/mcom"
mkdir -p "steps/website/bcom"
mkdir -p "steps/MEW/mcom"
mkdir -p "steps/MEW/bcom"
mkdir -p "steps/responsive/mcom"
mkdir -p "steps/responsive/bcom"
mkdir "utils"

cd ../../../../../../../../..
cp ../../pom_template.xml pom.xml
cd ../../

echo "File structure has been created."
echo ""
echo "Please update $1/$2/pom.xml with your domain and project name"
echo ""
echo "Git will not recognize the folders until they have files in them. Please place files in any directories you want to keep"
read -n1 -r -p "Press any key to continue..." key