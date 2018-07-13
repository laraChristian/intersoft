export class ApiUtilities {

	
	private static _server: String = "http://localhost:28080/instrasoft-api/";
	private static _apiUrl: String = "intrasoft-api";
	private static _apiServiciesEndPoint: String = ApiUtilities._server.concat(ApiUtilities._apiUrl.toString());

	public static get server(): String {
		return this._server;
	}

	public static set server(value: String) {
		this._server = value;
	}

	public static get apiUrl(): String {
		return this._apiUrl;
	}

	public static set apiUrl(value: String) {
		this._apiUrl = value;
	}


	public static get apiServiciesEndPoint(): String {
		return this._apiServiciesEndPoint;
	}

	public static set apiServiciesEndPoint(value: String) {
		this._apiServiciesEndPoint = value;
	}

}