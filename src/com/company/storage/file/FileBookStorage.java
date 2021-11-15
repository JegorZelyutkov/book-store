package com.company.storage.file;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.storage.BookStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileBookStorage implements BookStorage {
    private static final String PATTERN = "%d %s %s %d %d";
    private static final String PATH = "books.txt";
    private static final String REGEX = " ";

    private static final int ID_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int PRICE_INDEX = 3;
    private static final int AUTHOR_ID_INDEX = 4;

    public static void main(String[] args) {
        FileBookStorage fileBookStorage = new FileBookStorage();
        System.out.println(fileBookStorage.getAll());
        fileBookStorage.updateAuthor(1, new Author(2, "sdsd", "sdsd"));
//        dbBookStorage.save(new Book("Test", "test", new Author(1, "sd", "sdsd"),22));
    }


    @Override
    public void save(Book book) {
        try {
            FileWriter fileWriter = new FileWriter(PATH, true);
            String format = String.format(PATTERN, getId(), book.getTitle(), book.getDescription(), book.getPrice(), book.getAuthor().getId());
            fileWriter.write(format);
            fileWriter.write("\n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getId() {
        return 0;
    }

    @Override
    public void delete(int id) {
        List<Book> listOfBooks = getAll();
        for (Book listOfBook : listOfBooks) {
            if (listOfBook.getId() == id) {
                listOfBooks.remove(listOfBook);
                break;
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Book listOfBook : listOfBooks) {
                fileWriter.write(String.format(PATTERN, listOfBook.getTitle(), listOfBook.getDescription(), listOfBook.getPrice(), listOfBook.getAuthor().getId()));
                fileWriter.write("\n");
                fileWriter.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(Book book) {
        List<Book> listOfBooks = getAll();
        for (Book listOfBook : listOfBooks) {
            if (listOfBook.equals(book)) {
                listOfBooks.remove(listOfBook);
                break;
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Book listOfBook : listOfBooks) {
                fileWriter.write(String.format(PATTERN, listOfBook.getTitle(), listOfBook.getDescription(), listOfBook.getPrice(), listOfBook.getAuthor().getId()));
                fileWriter.write("\n");
                fileWriter.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTitle(int id, String title) {
        List<Book> listOfBooks = getAll();
        for (Book listOfBook : listOfBooks) {
            if (listOfBook.getId() == id) {
                listOfBook.setTitle(title);
                break;
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Book listOfBook : listOfBooks) {
                fileWriter.write(String.format(PATTERN, listOfBook.getTitle(), listOfBook.getDescription(), listOfBook.getPrice(), listOfBook.getAuthor().getId()));
                fileWriter.write("\n");
                fileWriter.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateDescription(int id, String description) {
        List<Book> listOfBooks = getAll();
        for (Book listOfBook : listOfBooks) {
            if (listOfBook.getId() == id) {
                listOfBook.setDescription(description);
                break;
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Book listOfBook : listOfBooks) {
                fileWriter.write(String.format(PATTERN, listOfBook.getTitle(), listOfBook.getDescription(), listOfBook.getPrice(), listOfBook.getAuthor().getId()));
                fileWriter.write("\n");
                fileWriter.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAuthor(int id, Author author) {
        List<Book> listOfBooks = getAll();
        for (Book listOfBook : listOfBooks) {
            if (listOfBook.getId() == id) {
                listOfBook.setAuthor(author);
                break;
            }

        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Book listOfBook : listOfBooks) {
                fileWriter.write(String.format(PATTERN, listOfBook.getId(), listOfBook.getTitle(), listOfBook.getDescription(), listOfBook.getPrice(), listOfBook.getAuthor().getId()));
                fileWriter.write("\n");
            }
            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public boolean exist(String title) {
        return false;
    }

    private Author getAuthorById(int id) {
        try {
            FileReader fileReaderForAuthors = new FileReader("authors.txt");
            BufferedReader readerForAuthors = new BufferedReader(fileReaderForAuthors);
            String lineForAuthors;

            while ((lineForAuthors = readerForAuthors.readLine()) != null) {
                String[] splitsAuthors = lineForAuthors.split(REGEX);
                if ((Integer.parseInt(splitsAuthors[0])) == id) {
                    return new Author(id, splitsAuthors[1], splitsAuthors[2]);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> listOfBooks = new ArrayList<>();
        try {
            FileReader fileReaderForBooks = new FileReader(PATH);
            BufferedReader readerForBooks = new BufferedReader(fileReaderForBooks);
            String lineForBooks;
            while ((lineForBooks = readerForBooks.readLine()) != null) {
                String[] book = lineForBooks.split(REGEX);
                Author authorById = getAuthorById(Integer.parseInt(book[AUTHOR_ID_INDEX]));
                Book book1 = new Book(
                        Integer.parseInt(book[AUTHOR_ID_INDEX]),
                        book[TITLE_INDEX],
                        book[DESCRIPTION_INDEX], authorById,
                        Integer.parseInt(book[PRICE_INDEX]));
                listOfBooks.add(book1);
            }
            return listOfBooks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }
}
