#!/bin/bash

# http://vdp.cuzk.cz/
DATE_UKSH='20171031'
DATE_UVOH='20171103'
TARGET='../ruian_files'

if [ -d $TARGET ]; then
	rm $TARGET/*.gz
else
	mkdir $TARGET
fi

for i in {500001..599999}
do
  wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/soucasna/$DATE_UKSH\_OB_$i\_UKSH.xml.gz
done

wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/specialni/$DATE_UVOH\_ST_UVOH.xml.gz
wget -P $TARGET http://vdp.cuzk.cz/vymenny_format/soucasna/$DATE_UKSH\_ST_UKSH.xml.gz

