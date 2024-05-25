package book_model;

import java.util.Date;

public class Docgia {
	private int _id;
	private String _hoten;
	private String _ns;
	private String _diachi;
	private String _sdt;
	
	public Docgia() {
	}

	public Docgia(int _id, String _hoten, String _ns, String _diachi, String _sdt) {
		this._id = _id;
		this._hoten = _hoten;
		this._ns = _ns;
		this._diachi = _diachi;	
		this._sdt = _sdt;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_hoten() {
		return _hoten;
	}
	public void set_hoten(String _hoten) {
		this._hoten = _hoten;
	}
	public String get_ns() {
		return _ns;
	}
	public void set_ns(String _ns) {
		this._ns = _ns;
	}
	public String get_diachi() {
		return _diachi;
	}
	public void set_diachi(String _diachi) {
		this._diachi = _diachi;
	}
	public String get_sdt() {
		return _sdt;
	}
	public void set_sdt(String _sdt) {
		this._sdt = _sdt;
	}
	

}
