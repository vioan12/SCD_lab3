/**
 * Created by ioan on 11/7/17.
 */
public class Casca {

    private float pret1,pret2;
    private String nume, url1, url2, id;
    private int n;

    Casca()
    {
        n=0;
        nume="";
        id="";
        pret1=0;
        url1="";
        pret2=0;
        url2="";
    }

    public void Add(String valueofid, String valueofnume, Float valuofpret, String valueofurl)
    {
        if(n==0)
        {
            n=1;
            id=valueofid;
            nume=valueofnume;
            pret1=valuofpret;
            url1=valueofurl;

        }else{

            if(n==1){
                n=2;
                pret2=valuofpret;
                url2=valueofurl;
            }
        }
    }

    public String Get_id()
    {
        return this.id;
    }

    public String Get_nume()
    {
        return this.nume;
    }

    public int Get_n()
    {
        return this.n;
    }

    public Float Get_pret1()
    {
        return this.pret1;
    }

    public Float Get_pret2()
    {
        return this.pret2;
    }

    public String Get_url1()
    {
        return this.url1;
    }

    public String Get_url2()
    {
        return this.url2;
    }

}
