cd common-json-interface
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-listlayout-interface
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-collection
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-gson
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-listlayout-smart
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-utils
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-http
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-widget
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-base
call bintrayUpload.bat %1
cd ..
ping -n 15 127.0.0.1>nul

cd common-binding
call bintrayUpload.bat %1
cd ..