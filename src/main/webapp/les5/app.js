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
	const storObj = !!localStorage.getItem(city) ? JSON.parse(localStorage.getItem(city)) : false;
	if(storObj === false || new Date(storObj.modified) < new Date(new Date() - 600000)) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', `../restservices/weather?lat=${escape(lat)}&lon=${escape(lon)}`, true);
		xhr.send();
	
		xhr.onreadystatechange = e => {
			if(e.target.readyState !== 4) return;
			const res = JSON.parse(e.target.response);
			localStorage.setItem(city, JSON.stringify({
				modified: new Date(),
				data: res,
			}));

			document.getElementById('tempature').innerHTML = Math.floor(res.main.temp - 273.15) + '&deg;';
			document.getElementById('humidity').innerHTML = res.main.humidity + '%';
			document.getElementById('windspeed').innerHTML = res.wind.speed + ' M/s';
			document.getElementById('winddirection').innerHTML = formatWindDirection(res.wind.deg);
			document.getElementById('sunrise').innerHTML = formatDate(res.sys.sunrise);
			document.getElementById('sunset').innerHTML = formatDate(res.sys.sunset);
		};
	} else {
		if (new Date(storObj.modified) < new Date(new Date() - 600000)) localStorage.removeItem(city);
		document.getElementById('tempature').innerHTML = Math.floor(storObj.data.main.temp - 273.15) + '&deg;';
		document.getElementById('humidity').innerHTML = storObj.data.main.humidity + '%';
		document.getElementById('windspeed').innerHTML = storObj.data.wind.speed + ' M/s';
		document.getElementById('winddirection').innerHTML = formatWindDirection(storObj.data.wind.deg);
		document.getElementById('sunrise').innerHTML = formatDate(storObj.data.sys.sunrise);
		document.getElementById('sunset').innerHTML = formatDate(storObj.data.sys.sunset);
	}
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
			`<tr class="item">
				<td>${countries[country]['name']}</td>
				<td>${countries[country]['capital']}</td>
				<td>${countries[country]['region']}</td>
				<td>${countries[country]['surface']} m&sup2;</td>
				<td>${countries[country]['population']} mensen</td>
			</tr>`;
		const items = document.getElementsByTagName('tr');
		for(let i = 1; i < items.length; i++) {
			items[i].addEventListener('click', e => showWeather(countries[i-1].lat, countries[i-1].lng, countries[i-1].capital));
		}
	};
}

initPage();
})();