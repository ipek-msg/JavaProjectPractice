import java.util.Scanner;

public class Atm {

    /*
    ATM
    Kullanıcıdan giriş icin kart numarası ve sifresini isteyin,eger herhangi birini yanlıs girerse tekrar isteyin.
    Kart Numarası aralarda bosluk ile girersede eger dogruysa kabul edin
    Kart Numarası ve sifre dogrulanırsa kullanıcının yapabilecegi işlemleri ekrana yazdırın

    Menu listesinde Bakiye sorgula,para yatırma, para cekme,para gönderme,sifre degistirme ve cıkıs işlemleri olacaktır.

    Para cekme ve para gönderme işleminde mevcur bakiyeden buyuk para cekilemez,
    Para gönderme işlemnde istenen iban TR ile baslamalı ve toplam 26 karakterli olmalı,eger degilse menü ekranına geri
    dönsün
    Sifre degistirme islemimde mevcut şifreyi teyit ettikten sonra ,sifre degişiklik işlemi yapmalı.
     */

    final String kartNo = "1234567890123456"; //bir değişiklik yapılmayacağı için final tanımladık
    //final da değişiklik yapılamaz
    static Scanner scan = new Scanner(System.in);//diğer methodlarla kullanabilmek için mainin dışında yazdık.
    //diğer static methodlarda kullanılabilmesi için static yapıldı.
    static String sifre = "1234";

    static double bakiye = 20000;

    public static void main(String[] args) {
        giris();


    }

    private static void giris() {
        System.out.println("Kart Numarasını Giriniz :");
        String kKartNo = scan.nextLine();
        System.out.println("Sifre Giriniz :");
        String kSifre = scan.next();

        //Kart Numarası aralarda bosluk ile girersede eger dogruysa kabul edin

        kKartNo = kKartNo.replaceAll("\\s", "");

        //replaceAll hepsi demek
        // "\\s" boşluk demek
        // boşlukları hiçlik ile degistirdik

        if (kSifre.equals(sifre) && kKartNo.equals(kKartNo)) {

            menu();
        }
    }


    private static void menu() {
        System.out.println("*****JAVABANKA HOSGELDİNİZ*****\n" +
                "YAPMAK İSTEDİGİNİZ İSLEMİ SECİNİZ \n" +
                "1.BAKİYE SORGULAMA \n" +
                "2.PARA YATIRMA \n" +
                "3.PARA CEKME \n" +
                "4.PARA GÖNDERME\n" +
                "5.SIFRE DEGİSTİRME \n" +
                "6.CIKIS ");

        int secim = scan.nextInt();
        switch (secim) {
            case 1: {
                bakiyeSorgula();
            }
            case 2: {
                System.out.println("YATIRILACAK MİKTARI GİRİNİZ :");
                double miktar = scan.nextDouble();
                paraYatirma(miktar);
            }
            case 3: {
                System.out.println("CEKİLECEK MİKTARI GİRİNİZ :");
                double miktar = scan.nextDouble();
                paraCekme(miktar);

            }
            case 4: {
                System.out.println("IBAN GIRINIZ:");
                String iban = scan.nextLine();
                scan.nextLine();  //ard arda nextline next yapınca sorun oluyor araya bir dammy atılıyor
                System.out.println("GONDERİLECEK MİKTARİ GİRİNİZ : ");
                double miktar = scan.nextDouble();
                ibanKontrol(iban);

                paraGonderme(ibanKontrol(iban), miktar);
            }
            case 5: {
                sifreDegistir();
            }
            case 6: {
                System.out.println("********HOSCAKALIN*******");
                System.exit(0);
            }


        }
    }

    private static void sifreDegistir() {
        System.out.println("SİFRENİZİ GİRİNİZ :");
        String kSifre=scan.next();
        if(kSifre.equals(sifre)){
            System.out.println("YENİ SİFRE GİRİNİZ :");
            sifre=scan.next();
            giris();
        }else{
            System.out.println("DOGRU SIFRE GİRİNİZ :");
            sifreDegistir();
        }
    }

    private static void paraGonderme(String iban, double miktar) {
        if (miktar <=bakiye){
            bakiye -=miktar;
            bakiyeSorgula();
            menu();
        }else {
            System.out.println("GECERLİ MIKTAR GIRINIZ :");

            paraGonderme(iban, scan.nextDouble());
        }
    }

    private static String ibanKontrol(String iban) {
        iban = iban.replaceAll("\\s", "");
        if (iban.startsWith("TR") && iban.length() == 26) { //startsWith başlangıç karakteri demektir.
            // TR ile başlayacak =True uzunluğu 26 karaktere eşit olacak
        } else {
            System.out.println("GECERLİ IBAN GİRİNİZ");
            ibanKontrol(scan.nextLine());

        }
        return iban;
    }

    private static void paraCekme(double miktar){
        if (miktar <= bakiye) {
            bakiye -= miktar;
            bakiyeSorgula();
            menu();
        } else {
            System.out.println("GECERLİ MİKTAR GİRİNİZ");
            paraCekme(scan.nextDouble());
        }
    }



    private static void paraYatirma ( double miktar){

            bakiye += miktar;
            bakiyeSorgula();
            menu();

        }
        private static void bakiyeSorgula () {
                System.out.println("BAKİYENİZ : " + bakiye);
        menu();

    }

}

