package kasir;

public class barang {
	
	private Integer sku, stock, harga_beli, harga_jual;
	private String nama;
	
	public barang(Integer sku, String nama, Integer stock, Integer harga_beli, Integer harga_jual) {
		setSku(sku);
		setNama(nama);
		setStock(stock);
		setHarga_beli(harga_beli);
		setHarga_jual(harga_jual);
	}
	
	public Integer getSku() {
		return sku;
	}
	
	public void setSku(Integer sku) {
		this.sku = sku;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Integer getHarga_beli() {
		return harga_beli;
	}
	
	public void setHarga_beli(Integer harga_beli) {
		this.harga_beli = harga_beli;
	}
	
	public Integer getHarga_jual() {
		return harga_jual;
	}
	
	public void setHarga_jual(Integer harga_jual) {
		this.harga_jual = harga_jual;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}

}
