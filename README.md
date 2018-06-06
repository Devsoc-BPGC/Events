# Events App
A modular events app for all types of events.

## Usage
Make sure you have latest android studio. Download this repository and build using android studio.

## Technologies/Libraries used

### Code Quality
#### Checkstyle
To ensure your commits adhere to proper code style, please execute the following before sending a
pull request and solve the issues detected in the report.

```bash
mkdir app/build/reports/ -p
checkStyleVer=8.10
wget https://github.com/checkstyle/checkstyle/releases/download/checkstyle-$checkStyleVer/checkstyle-$checkStyleVer-all.jar -c --quiet
java -jar checkstyle-$checkStyleVer-all.jar -c config/checkstyle/checkstyle.xml app/src/ -o app/build/reports/checkstyle.txt
# open the report in your favorite text viewer
less app/build/reports/checkstyle.txt
```

## Web Interface
Coming soon
