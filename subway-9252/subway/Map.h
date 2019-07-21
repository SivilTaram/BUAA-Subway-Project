#pragma once

#include <vector>
#include <string>
#include <map>
#include "Line.h"
#include "Path.h"



class Map
{
public:
	//���е�վ��
	map<string, Station> Stations;
	//���еĵ�����
	map<string, Line<Station>> Lines;

	Map(vector<string> lineString);
	Map();

};





