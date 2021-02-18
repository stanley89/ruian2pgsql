#!/bin/bash

# http://vdp.cuzk.cz/
DATE_UKSH='20210131'
DATE_UVOH='20210203'
TARGET='./zip'

if [ -d $TARGET ]; then
	rm $TARGET/*.zip
else
	mkdir $TARGET
fi

for i in {500001..599999}
do
  wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/soucasna/$DATE_UKSH\_OB_$i\_UKSH.xml.zip
done

wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/specialni/$DATE_UVOH\_ST_UVOH.xml.zip
wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/soucasna/$DATE_UKSH\_ST_UKSH.xml.zip

