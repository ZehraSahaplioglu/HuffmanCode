package huffmanodev;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class HuffmanOdev {

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

        Scanner s = new Scanner(System.in);

        int n = 10;
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k'};
        int[] charfreq = {5, 19, 12, 13, 16, 45, 55, 60, 4, 68};

        // öncelikli kuyruk oluşturması
        
        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new MyComparator());

        for (int i = 0; i < n; i++) {

            // bir Huffman düğüm nesnesi oluşturmak ve onu öncelik sırasına eklemek.
            
            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.data = charfreq[i];

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


