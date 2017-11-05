public class Main {

    private static Thread[] thread=new Thread[2];
    public static void main(String[] args) {

        HtmlExtractCel HEC = new HtmlExtractCel("http://www.cel.ro/casti/");
        HEC.extract_all_products();

        HtmlExtractEmag HEE= new HtmlExtractEmag("https://www.emag.ro/casti-pc/p1/c");
        HEE.extract_all_products();

    }
}
