#include "Route.h"


Route::Route(shared_ptr<Station> startStation)
	:changeCount_(0)
{
	shared_ptr<NextStation> station = make_shared<NextStation>();
	station->routeLine = "0";
	station->station = startStation;
	stations_.push_back(station);
}

int Route::addNextStation(shared_ptr<NextStation> station)
{
	if (station->routeLine != stations_.back()->routeLine) {
		changeCount_++;
	}
	if (find(stations_.begin(), stations_.end(), station) != stations_.end()) {
		//վ���Ѿ�����
		return -1;
	}
	stations_.push_back(station);
	return 0;
}

int Route::generateNextRoute(list<shared_ptr<Route>>& routes)
{
	shared_ptr<Station> endStaion = getEndStaion();
	for (auto station : endStaion->nextStations)
	{
		shared_ptr<Route> newRoute = make_shared<Route>(*this);
		if (newRoute->addNextStation(station) == 0) {
			routes.push_back(newRoute);
		};
	}
	return 0;
}

shared_ptr<Station> Route::getStartStaion()
{
	if (stations_.empty()) return nullptr;
	return (stations_.front()->station);
}

shared_ptr<Station> Route::getEndStaion()
{
	if (stations_.empty()) return nullptr;
	return (stations_.back()->station);
}

void Route::dump(ostream& stream) const
{
	if (stations_.empty()) return;
	stream << stations_.size() << endl;
	auto iter = stations_.begin();
	stream << (*iter)->station->name_ << endl;
	iter++;
	string lineName = (*iter)->routeLine;
	for (;iter!=stations_.end();iter++)
	{
		auto station = *iter;
		if (lineName != station->routeLine) {
			stream << station->routeLine << endl;
			lineName = station->routeLine;
		};
		stream << station->station->name_ << endl;
	}
}
