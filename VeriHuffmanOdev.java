package verihuffmanodev;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VeriHuffmanOdev {

    // secim değişkeninde bizim alfabemiz mevcut, buradakilerden farklı girdiler dikkate alınmaz.
    static String secim = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String hashMap;

    public static void printCode(HuffmanNode root, String s) {

        //basit durum; sol ve sağ null ise bu bir yaprak düğümdür ve ağacı çaprazlayarak oluşturulan kodları yazdırırız.
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            System.out.println(root.c + ":" + s);

            return;
        }

        // sol taraf 0 kodunu alır
        // sağ taraf 1 kodunu alır
        // oluşturulan ağacın sol ve sağ alt ağacı için özyinelemeli çağırılır.
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {

        System.out.println("Lütfen bu alfabenin dışında kalan bir harf kullanmayın: " + secim);
        Scanner girdi = new Scanner(System.in);
        System.out.print("lütfen bir cumle giriniz: ");
        String cumle = girdi.nextLine();

        HashMap<Object, Object> hmap = new HashMap<Object, Object>();

        for (int i = 0; i < cumle.length(); i++) {
            if (secim.indexOf(cumle.charAt(i)) >= 0) {
                if (!hmap.containsKey(cumle.charAt(i))) {
                    hmap.put(cumle.charAt(i), 1);
                } else {
                    int count = (int) hmap.get(cumle.charAt(i));
                    hmap.put(cumle.charAt(i), count + 1);
                }
            }

        }
        System.out.println(cumle); // cümleyi yazdırır
        System.out.println(hmap); // hmap' yazdırır

        Object[] a = hmap.keySet().toArray(); // dizinin anahtarlarını a dizisine atar
        Object[] b = hmap.values().toArray(); // dizinin değerlerini b dizisine atar

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        // öncelikli kuyruk oluşturması
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(cumle.length(), new MyComparator());

        for (int i = 0; i < cumle.length(); i++) {

            // bir Huffman düğüm nesnesi oluşturmak ve onu öncelik sırasına eklemek.
            HuffmanNode hn = new HuffmanNode();

            hn.c = (char) (Object) a[i];
            hn.data = (int) (Object) b[i];

            hn.left = null;
            hn.right = null;

            // add fonksiyonu gelen veriyi huffman düğümü olarak ekler
            q.add(hn);
        }

        HuffmanNode root = null;

        // Burada, boyutu 1'e düşene kadar her seferinde yığından iki minimum değeri çıkaracağız, 
        // tüm düğümler çıkarılana kadar devam edeceğiz.
        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            // iki düğümün frekanslarının toplamı
            f.data = x.data + y.data;
            f.c = '-';

            // sol çocuk tarafında ilk çıkarılan düğüm
            f.left = x;

            // sağ çocuk tarafında ikinci çıkarılan düğüm
            f.right = y;

            root = f;

            // bu düğümü öncelik sırasına ekleyin.
            q.add(f);
        }

        // ağacı çaprazlayarak kodları yazdır
        printCode(root, "");

    }

}

// düğüm sınıfı, Huffman ağacında bulunan her düğümün temel yapısıdır.
class HuffmanNode {

    int data;
    char c;

    HuffmanNode left;
    HuffmanNode right;
}

// karşılaştırıcı sınıfı, düğümün özniteliklerinden birine göre karşılaştırılmasına yardımcı olur.
// Burada düğümlerin veri değerleri bazında karşılaştırılacağız.
class MyComparator implements Comparator<HuffmanNode> {

    @Override
    public int compare(HuffmanNode x, HuffmanNode y) {

        return x.data - y.data;
    }
}



