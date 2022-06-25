package ppaa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class Ppaa extends JFrame {
    int[][] labirin = new int[][] { // Array untuk Maze
            { 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
            { 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0 },
            { 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0 },
            { 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 3, 0, 0 },
            { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0 },
            { 0, 0, 4, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
            // 0=jalan
            // 1=pembatas
            // 2=origin
            // 3=destination
            // 4=kurir
    };

    // Button
    JButton RandomCourier;
    JButton RandomOrigin;
    JButton RandomDestination;
    JButton RandomLabirin;
    JButton Solution;
    JButton exit;

    // Random Array
    boolean repaint = false;

    public Ppaa() {
        // Pengaturan Frame
        setTitle("Smart Courier");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        // Menambahkan Objek Button
        RandomCourier = new JButton("Random Courier");
        RandomOrigin = new JButton("Random Origin");
        RandomDestination = new JButton("Random Destination");
        RandomLabirin = new JButton("Random Map Labirin");
        Solution = new JButton("Solution");
        exit = new JButton("Exit");
        
        add(RandomCourier);
        add(RandomOrigin);
        add(RandomDestination);
        add(RandomLabirin);
        add(Solution);
        add(exit);
        
        // Posisi Objek Button
        RandomCourier.setBounds(700, 40, 170, 40);
        RandomOrigin.setBounds(700, 100, 170, 40);
        RandomDestination.setBounds(700, 160, 170, 40);
        RandomLabirin.setBounds(700, 220, 170, 40);
        Solution.setBounds(700, 280, 170, 40);
        exit.setBounds(700, 340, 170, 40);        
        
        // Jika menekan Random Courier Button
        RandomCourier.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = RandomCourier();
                restore(x);
                repaint();
            }
        });

        // Jika menekan Random Origin Button
        RandomOrigin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = RandomOrigin();
                restore(x);
                repaint();
            }
        });

        // Jika menekan Random Destination Button
        RandomDestination.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = RandomDestination();
                repaint = true;
                restore(x);
                repaint();
            }
        });

        // Jika menekan Random Labirin Button
        RandomLabirin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x[][] = RandomLabirin();
                repaint = true;
                restore(x);
                repaint();
            }
        });
        
        // Jika menekan Exit Button
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);        //Close The App
            }
        });
        
        /*
        
        //Jika menekan Solution Button
        Solution.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Solution();
                } catch (Exception e1) {
                    System.exit(0);
                    e1.printStackTrace();
                }

            }
        });    
        
        */
    }
        
    // Buat array yang akan datang sama dengan array Labirin
    // Untuk mengembalikan Labirin ke keadaan awal
    public void restore(int[][] savedLabirint) {
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                labirin[i][j] = savedLabirint[i][j];
            }
        }
    }

    // Fungsi Random Labirin
    public int[][] RandomLabirin() {
        int[][] arr;
        arr = new int[14][14];
        Random rnd = new Random();
        int min = 0;
        int high = 1;

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
              if(i%2==1 && j%2==0){
                  int n = rnd.nextInt((high - min) + 1) + min;
                    arr[i][j] = n;
              }
            }
        }
        
        // mempertahankan posisi origin
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 2) {
                    arr[i][j] = 2;
                }
            }
        }
        
        // mempertahankan posisi courier
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4) {
                    arr[i][j] = 4;
                }
            }
        }

        // mempertahankan posisi destination
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }
        return arr;
    }
    
    // Fungsi Random Origin
    public int[][] RandomOrigin() {
        int[][] arr;
        arr = new int[labirin.length][labirin.length];

        Random row = new Random();
        Random col = new Random();

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                switch (labirin[i][j]) {
                    case 0:
                        arr[i][j] = 0;
                        break;
                    case 1:
                        arr[i][j] = 1;
                        break;
                    case 3:
                        arr[i][j] = 3;
                        break;
                    case 4:
                        arr[i][j] = 4;
                        break;
                }
            }
        }

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                if (labirin[i][j] == 2) {

                    while (true) {
                        int m = row.nextInt(labirin.length) + 0;
                        int n = col.nextInt(labirin.length) + 0;
                        if (labirin[m][n] == 0) {
                            arr[m][n] = 2;
                            break;
                        } else {
                            continue;
                        }
                    }

                }
            }
        }
        return arr;
    }

    // Fungsi Random Courier 
    public int[][] RandomCourier() {
        int[][] arr;
        arr = new int[labirin.length][labirin.length];

        Random row = new Random();
        Random col = new Random();

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                switch (labirin[i][j]) {
                    case 0:
                        arr[i][j] = 0;
                        break;
                    case 1:
                        arr[i][j] = 1;
                        break;
                    case 2:
                        arr[i][j] = 2;
                        break;
                    case 3:
                        arr[i][j] = 3;
                        break;
                }
            }
        }

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                if (labirin[i][j] == 4) {

                    while (true) {
                        int m = row.nextInt(labirin.length) + 0;
                        int n = col.nextInt(labirin.length) + 0;
                        if (labirin[m][n] == 0) {
                            arr[m][n] = 4;
                            break;
                        } else {
                            continue;
                        }
                    }

                }
            }
        }
        return arr;
    }

    // Fungsi Random Destination
    public int[][] RandomDestination() {
        int[][] arr;
        arr = new int[labirin.length][labirin.length];

        Random row = new Random();
        Random col = new Random();

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                switch (labirin[i][j]) {
                    case 0:
                        arr[i][j] = 0;
                        break;
                    case 1:
                        arr[i][j] = 1;
                        break;
                    case 2:
                        arr[i][j] = 2;
                        break;
                    case 4:
                        arr[i][j] = 4;
                        break;
                }
            }
        }

        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                if (labirin[i][j] == 3) {

                    while (true) {
                        int m = row.nextInt(labirin.length) + 0;
                        int n = col.nextInt(labirin.length) + 0;
                        if (labirin[m][n] == 0) {
                            arr[m][n] = 3;
                            break;
                        } else {
                            continue;
                        }
                    }

                }
            }
        }
        return arr;
    }
    
    /*
    
    // Mencari posisi baris Courier
    public int GetRowCourier() {
        int a = 0;
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4) {
                    a = i;
                }
            }
        }
        return a;
    }

    // Mencari posisi colom Courier
    public int GetColCourier() {
        int b = 0;
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4) {
                    b = j;
                }
            }
        }
        return b;
    } 
    
    // Mencari posisi baris Destination
    public int GetRowDestination() {
        int a = 0;
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4) {
                    a = i;
                }
            }
        }
        return a;
    }

    // Mencari posisi colom Destination
    public int GetColDestination() {
        int b = 0;
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4) {
                    b = j;
                }
            }
        }
        return b;
    } 
    
    // Fungsi Solution
    public void Solution(){
        int[][] arr;
        arr = new int[14][14];
        int x = GetRowCourier();// memanggil fungsi mencari Courier
        int y = GetColCourier();
        int r = GetRowDestination();// memanggil fungsi mencari Destination
        int s = GetColDestination();
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {

                if (labirin[i][j] == 0) {
                    arr[i][j] = 0;
                } else if (labirin[i][j] == 1) {
                    arr[i][j] = 1;
                } else if (labirin[i][j] == 2) {
                    arr[i][j] = 2;
                } else if (labirin[i][j] == 3) {
                    arr[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < labirin.length; i++) {
            for (int j = 0; j < labirin.length; j++) {
                if (labirin[i][j] == 4
                        && (labirin[i][j + 1] == 2 || labirin[i][j - 1] == 2 || labirin[i - 1][j] == 2 || labirin[i + 1][j] == 2)) {
                    if (x < r && y < s) {
                        // jika kondisi satu atau lebih kotak di sekelilingnya 0
                        if (labirin[i - 1][j] == 0 || labirin[i + 1][j] == 0 || labirin[i][j - 1] == 0 ||
                                labirin[i][j + 1] == 0) {
                            if (labirin[i][j + 1] == 3 || labirin[i + 1][j] == 3) {
                                arr[i][j] = 4;
                            }
                            // kondisi yang mengakibatkan courier jalan ke kanan
                            else if (labirin[i][j + 1] == 0 && (labirin[i + 1][j] == 2
                                    || labirin[i + 1][j] == 1
                                    || labirin[i][j - 1] == 1 || (labirin[i + 1][j + 1] == 1))) {

                                arr[i][j + 1] = 4;

                            }
                            // kondisi yang mengakibatkan courier jalan ke bawah
                            else if (maze[i + 1][j] == 0) {

                                arr[i + 1][j] = 4;

                            }
                            // kondisi yang mengakibatkan courier jalan ke bawah
                            else if (maze[i + 1][j] == 0 && (maze[i + 1][j + 1] == 1 && y == q - 1)) {

                                arr[i + 1][j] = 4;

                            }
                            // kondisi yang mengakibatkan courier jalan ke kanan
                            else if (maze[i][j + 1] == 0 && (maze[i + 1][j] == 1
                                    || maze[i][j - 1] == 1 || (maze[i + 1][j + 1] == 1 && x == p - 1))) {

                                arr[i][j + 1] = 4;

                            } else if (maze[i + 1][j] == 0) {

                                arr[i + 1][j] = 4;

                            }

                            // kondisi yang mengakibatkan courier jalan ke kiri
                            else if ((maze[i][j - 1] == 0 && maze[i + 1][j] == 1 && maze[i][j + 1] == 1)) {
                                arr[i][j - 1] = 4;

                            }

                            // kondisi yang mengakibatkan corier jalan ke atas
                            else if (maze[i - 1][j] == 0 && maze[i][j + 1] == 1 && (maze[i][j - 1] == 1)
                                    && ((maze[i + 1][j] == 1)
                                            || maze[i + 2][j] == 1)) {
                                arr[i - 1][j] = 4;
                            }
                        }
                    }
                }
            }
        }
    }
    
    */
    
    // Gambar labirin pada JFrame
    @Override
    public void paint(Graphics g) {
        g.translate(70, 70); // pindahkan labirin mulai dari 70 dari x dan 70 dari y

        // Gambar Labirin
        if (repaint == true) {
            for (int row = 0; row < labirin.length; row++) {
                for (int col = 0; col < labirin[0].length; col++) {
                    Color color;
                    switch (labirin[row][col]) {
                        case 1:
                            color = Color.DARK_GRAY;
                            break;
                        case 2:
                            color = Color.YELLOW;
                            break;
                        case 3:
                            color = Color.PINK;
                            
                            break;
                        case 4:
                            color = Color.GREEN;
                            break;
                        default:
                            color = Color.decode("#FEECC8");
                    }
                    
                    g.setColor(color);
                    g.fillRect(40 * col, 40 * row, 40, 40); // fill rectangular with color
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(40 * col, 40 * row, 40, 40); // draw rectangular with color

                }
            }
        }

        if (repaint == false) {
            for (int row = 0; row < labirin.length; row++) {
                for (int col = 0; col < labirin[0].length; col++) {
                    Color color;
                    switch (labirin[row][col]) {
                        case 1:
                            color = Color.DARK_GRAY;
                            break;
                        case 2:
                            color = Color.YELLOW;
                            break;
                        case 3:
                            color = Color.PINK;
                            break;
                        case 4:
                            color = Color.GREEN;
                            break;
                        default:
                            color = Color.decode("#FEECC8");
                    }
               
                    g.setColor(color);
                    g.fillRect(40 * col, 40 * row, 40, 40);
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(40 * col, 40 * row, 40, 40);

                }

            }

        }

    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            //untuk merun file
            public void run() {
                Ppaa ppaa = new Ppaa();

            }
        });

    }
}