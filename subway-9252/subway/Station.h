#pragma once

#include <vector>
#include <string>
#include "Line.h"

using namespace std;

 class Station {
public:
	string StationName;//վ������
	vector<string> BelongTo ;//�����ߵ�����
	vector<int> IndexOfLine;//��Ӧ�ĵ������ϵ�վ���
	bool Visited;//�Ƿ��ѱ�����
	Station();
	Station(string name);
	Station& operator=(Station& p);
	
};