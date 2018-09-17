@echo off

set argC=0
for %%x in (%*) do set /A argC+=1

if "%argC%" == "2"  goto create_domain
echo "Error: no domain/project info"
echo "Usage: create_project.bat <domain> <project>"
echo "		e.g.: create_project SolutionDevelopment SampleProject"
PAUSE
exit

:create_domain
if exist "%1" goto create_project
mkdir %1

:create_project
cd %1
if exist "%2" goto create_dirs
mkdir %2

:create_dirs
cd %2
mkdir "features"
mkdir "src\main\java\com\macys\sdt\projects\%1\%2"
cd "src\main\"
mkdir "resources\elements\website\mcom\pages"
mkdir "resources\elements\website\mcom\panels"
mkdir "resources\elements\website\bcom\pages"
mkdir "resources\elements\website\bcom\panels"
mkdir "resources\elements\MEW\mcom\pages"
mkdir "resources\elements\MEW\mcom\panels"
mkdir "resources\elements\MEW\bcom\pages"
mkdir "resources\elements\MEW\bcom\panels"
mkdir "resources\elements\responsive\mcom\pages"
mkdir "resources\elements\responsive\mcom\panels"
mkdir "resources\elements\responsive\bcom\pages"
mkdir "resources\elements\responsive\bcom\panels"
cd "java\com\macys\sdt\projects\%1\%2"
mkdir "actions\website\mcom\pages"
mkdir "actions\website\mcom\panels"
mkdir "actions\website\bcom\pages"
mkdir "actions\website\bcom\panels"
mkdir "actions\MEW\mcom\pages"
mkdir "actions\MEW\mcom\panels"
mkdir "actions\MEW\bcom\pages"
mkdir "actions\MEW\bcom\panels"
mkdir "actions\responsive\mcom\pages"
mkdir "actions\responsive\mcom\panels"
mkdir "actions\responsive\bcom\pages"
mkdir "actions\responsive\bcom\panels"
mkdir "steps\website\mcom"
mkdir "steps\website\bcom"
mkdir "steps\MEW\mcom"
mkdir "steps\MEW\bcom"
mkdir "steps\responsive\mcom"
mkdir "steps\responsive\bcom"
mkdir "utils"

cd ..\..\..\..\..\..\..\..\..
copy ..\..\pom_template.xml pom.xml
cd ..\..\

echo File structure has been created
echo.
echo Please update %1\%2\pom.xml with your domain and project name
echo.
echo Git will not recognize the folders until they have files in them. Please place files in any directories you want to keep
PAUSE
