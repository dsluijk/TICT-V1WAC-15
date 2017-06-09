(() => {
"use strict";

let countries = [];

const initPage = () => {
	loadCountries();
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '../restservices/ip', true);
	xhr.send();

	xhr.onreadystatechange = e => {
		if(e.target.readyState !== 4) return;
		const res = JSON.parse(e.target.response);

		document.getElementById('countrycode').innerHTML = res.countryCode;
		document.getElementById('country').innerHTML = res.country;
		document.getElementById('region').innerHTML = res.regionName;
		document.getElementById('city').innerHTML = res.city;
		document.getElementById('postalcode').innerHTML = res.zip;
		document.getElementById('lat').innerHTML = res.lat;
		document.getElementById('lon').innerHTML = res.lon;
		document.getElementById('ip').innerHTML = res.query;

		showWeather(res.lat, res.lon, res.city);
	};
}

const showWeather = (lat, lon, city) => {
	document.getElementById('cityDisplay').innerHTML = city;

	var xhr = new XMLHttpRequest();
	xhr.open('GET', `../restservices/weather?lat=${escape(lat)}&lon=${escape(lon)}`, true);
	xhr.send();

	xhr.onreadystatechange = e => {
		if(e.target.readyState !== 4) return;
		const res = JSON.parse(e.target.response);
		document.getElementById('tempature').innerHTML = Math.floor((res.main.temp - 273.15)) + '&deg;';
		document.getElementById('humidity').innerHTML = res.main.humidity + '%';
		document.getElementById('windspeed').innerHTML = res.wind.speed + ' M/s';
		document.getElementById('winddirection').innerHTML = formatWindDirection(res.wind.deg);
		document.getElementById('sunrise').innerHTML = formatDate(res.sys.sunrise);
		document.getElementById('sunset').innerHTML = formatDate(res.sys.sunset);
	};
}

const formatWindDirection = degree => {
	var part = Math.floor((degree / 22.5) + 0.5);
    var directions = ["N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"];
    return directions[(part % 16)];
}

const formatDate = unixDate => {
	const sunrise = new Date(unixDate*1000);
	return sunrise.getHours() + ':' + sunrise.getMinutes();
}

const loadCountries = () => {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '../restservices/countries', true);
	xhr.send();

	xhr.onreadystatechange = e => {
		if(e.target.readyState !== 4) return;
		countries = countries.concat(JSON.parse(e.target.response));
		for(const country in countries) document.getElementById('countryTable').innerHTML +=
			`<tr>
				<td>${countries[country]['name']}</td>
				<td>${countries[country]['capital']}</td>
				<td>${countries[country]['region']}</td>
				<td>${countries[country]['surface']}</td>
				<td>${countries[country]['population']}</td>
			</tr>`;
		const items = document.getElementsByTagName('tr');
		for(let i = 0; i < items.length; i++) {
			if(i === 0) continue;
			items[i].addEventListener('click', e => showWeather(countries[i].lat, countries[i].lng, countries[i].capital));
		}
	};
}

initPage();
})();