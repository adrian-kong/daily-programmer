package C358.hard;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/8fbqhw/20180427_challenge_358_hard_puzzle_me_this/
 *
 * As I am no designer the input will be purely numerical, feel free to make some visual version of the jigsaw puzzles :)
 * You will first be given the dimension as X, Y
 * Afterwards you will be given list of puzzle pieces and what type their 4 sides connect to (given as up, right, down, left)
 * Their side-connection is given as a number, They connect with their negated number
 * this means that a 1 and -1 connects, 2 and -2 connects etc.
 * 0 means that it doesnt connect with anything.
 * Assume pieces are rotated in the correct direction.
 *
 * @author Adrian
 * @since 29 Apr 2018
 */
public class Hard {

    /**
     * TODO: everything so far is hardcoded. doesn't work though. need to re do this one another time.
     */

    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList();
        try (BufferedReader reader = Files.newBufferedReader(new File("src/C358/hard/5by5.txt").toPath())) {
            reader.lines().forEach(a -> list.add(a.replaceAll(" ", "")));
        }
        String[] size = list.remove(0).split(",");
        int sizeX = Integer.parseInt(size[0]);
        int sizeY = Integer.parseInt(size[1]);

        ArrayList<Piece> pieces = new ArrayList();
        for (String str : list) {
            String[] format = str.split(":");
            int id = Integer.parseInt(format[0]);
            int[] connection = new int[]{Integer.parseInt(format[1].split(",")[0]),
                    Integer.parseInt(format[1].split(",")[1]),
                    Integer.parseInt(format[1].split(",")[2]),
                    Integer.parseInt(format[1].split(",")[3])};
            pieces.add(new Piece(id, connection));
        }

        Board board = new Board(sizeX, sizeY);
        ArrayList<Piece> topPieces = new ArrayList();
        ArrayList<Piece> bottomPieces = new ArrayList();
        ArrayList<Piece> rightPieces = new ArrayList();
        ArrayList<Piece> leftPieces = new ArrayList();
        ArrayList<Piece> corners = new ArrayList();

        for (Piece piece : pieces) {
            if (piece.corner() != -1) {
                int x = 0;
                int y = 0;
                if (piece.corner() == 1) {
                    x = sizeX - 1;
                    y = 0;
                }
                if (piece.corner() == 2) {
                    x = sizeX - 1;
                    y = sizeY - 1;
                }
                if (piece.corner() == 3) {
                    x = 0;
                    y = sizeY - 1;
                }
                board.addPiece(x, y, piece);
                corners.add(piece);
            }
            if (piece.edge() != -1) {
                if (piece.edge() == 0) {
                    topPieces.add(piece);
                }
                if (piece.edge() == 1) {
                    rightPieces.add(piece);
                }
                if (piece.edge() == 2) {
                    bottomPieces.add(piece);
                }
                if (piece.edge() == 3) {
                    leftPieces.add(piece);
                }
            }
        }
        pieces.removeAll(leftPieces);
        pieces.removeAll(rightPieces);
        pieces.removeAll(topPieces);
        pieces.removeAll(bottomPieces);
        pieces.removeAll(corners);

        Row firstRow = board.rows[0];
        while (firstRow.getFirstNull() != null) {
            int req = -firstRow.getFirstNull().getRight();
            for (Piece piece : topPieces) {
                if (piece.getLeft() == req) {
                    board.addPiece(firstRow.getFirstNull().x + 1, 0, piece);
                }
            }
        }

        Row lastRow = board.rows[sizeY - 1];
        while (lastRow.getFirstNull() != null) {
            int req = -lastRow.getFirstNull().getRight();
            for (Piece piece : bottomPieces) {
                if (piece.getLeft() == req) {
                    board.addPiece(lastRow.getFirstNull().x + 1, sizeY - 1, piece);
                }
            }
        }
        while (board.getVertNull(0) != null) {
            int req = -board.getVertNull(0).getDown();
            for (Piece piece : leftPieces) {
                if (piece.getUp() == req) {
                    board.addPiece(0, board.getVertNull(0).y + 1, piece);
                }
            }
        }
        while (board.getVertNull(sizeX - 1) != null) {
            int req = -board.getVertNull(sizeX - 1).getDown();
            for (Piece piece : rightPieces) {
                if (piece.getUp() == req) {
                    board.addPiece(sizeX - 1, board.getVertNull(sizeX - 1).y + 1, piece);
                }
            }
        }
        board.print();

    }

    static class Board {

        Row[] rows;

        int length;

        public Board(int sizeX, int sizeY) {
            rows = new Row[sizeX];
            for (int i = 0; i < sizeX; i++) {
                rows[i] = new Row(sizeY);
            }
            length = sizeY;
        }

        public void addPiece(int x, int y, Piece piece) {
            if (rows[y].pieces[x] != null) {
                System.out.println("ERROR not null at " + x + " " + y + " " + piece.id);
            }
            rows[y].pieces[x] = piece;
            piece.x = x;
            piece.y = y;
        }

        public void print() {
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows[i].pieces.length; j++) {
                    if (rows[i].pieces[j] == null) {
                        System.out.print(" _ ");
                        continue;
                    }
                    System.out.print(rows[i].pieces[j].id + " ");
                }
                System.out.println();
            }
        }

        public Piece getVertNull(int index) {
            for (int i = 0; i < rows.length; i++) {
                if (rows[i].pieces[index] == null) {
                    return rows[i - 1].pieces[index];
                }
            }
            return null;
        }

    }

    static class Row {

        Piece[] pieces;

        public Row(int size) {
            pieces = new Piece[size];
        }

        public void add(int index, Piece piece) {
            if (pieces[index] != null) {
                System.out.println(index + " is not null");
            }
            pieces[index] = piece;
        }

        public Piece getFirstNull() {
            for (int i = 0; i < pieces.length; i++) {
                if (pieces[i] == null) {
                    return pieces[i - 1];
                }
            }
            return null;
        }
    }

    static class Piece {

        private int id;
        private int[] connection;

        private boolean[] edges;

        public int x, y;

        public Piece(int id, int[] connection) {
            this.id = id;
            this.connection = connection;
            edges = new boolean[]{getUp() == 0, getRight() == 0, getDown() == 0, getLeft() == 0};
        }

        public int corner() {
            if (edges[0] && edges[3]) {
                return 0;
            }
            if (edges[0] && edges[1]) {
                return 1;
            }
            if (edges[2] && edges[1]) {
                return 2;
            }
            if (edges[2] && edges[3]) {
                return 3;
            }
            return -1;
        }

        public int edge() {
            for (int i = 0; i < edges.length; i++) {
                if (edges[i]) {
                    return i;
                }
            }
            return -1;
        }

        public int getUp() {
            return connection[0];
        }

        public int getRight() {
            return connection[1];
        }

        public int getDown() {
            return connection[2];
        }

        public int getLeft() {
            return connection[3];
        }

    }
}
