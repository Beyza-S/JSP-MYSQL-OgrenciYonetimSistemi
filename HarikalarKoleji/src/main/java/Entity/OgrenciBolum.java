
package Entity;

public class OgrenciBolum {
    
  private Ogrenci ogrenci;
  private Bolum bolum ;
  
  public OgrenciBolum() {
       
    }

    public OgrenciBolum(Ogrenci ogrenci, Bolum bolum) {
        this.ogrenci = ogrenci;
        this.bolum = bolum;
    }

    public Ogrenci getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(Ogrenci ogrenci) {
        this.ogrenci = ogrenci;
    }

    public Bolum getBolum() {
        return bolum;
    }

    public void setBolum(Bolum bolum) {
        this.bolum = bolum;
    }
  
    }
  
  
    
    
    

