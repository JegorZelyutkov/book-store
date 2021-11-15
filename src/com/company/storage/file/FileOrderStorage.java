package com.company.storage.file;

import com.company.entity.*;
import com.company.storage.OrderStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileOrderStorage implements OrderStorage {
    private static final String ORDERS_PATH = "orders.txt";
    private static final int ID_ORDER = 0;
    private static final String SPACE = " ";

    public static void main(String[] args) {
        String pattern = "%d %d %d ";
        int count = 2;
        StringBuilder b = new StringBuilder(pattern);
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                b.append("%d,");
            } else {
                b.append("%d ");
            }
        }
        b.append("%s ");
        b.append("%d");
        String s = b.toString();
        System.out.println(s);
    }

    @Override
    public void save(Order order) {
        Book[] books = order.getBooks();
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                count = i;
            }
            System.out.println("No books");
        }
        String pattern = "%d %d %d ";
        StringBuilder stringBuilderPattern = new StringBuilder(pattern);
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                stringBuilderPattern.append("%d,");
            } else stringBuilderPattern.append("%d ");
        }
        stringBuilderPattern.append("%s ");
        stringBuilderPattern.append("%d");

        try {
            FileWriter fileWriter = new FileWriter(ORDERS_PATH, true);
            String format = String.format(stringBuilderPattern.toString(),
                    order.getId(),
                    order.getAddress().getId(),
                    order.getStore().getId(),
                    order.getBooks(),
                    order.getType(),
                    order.getUser().getId());
            fileWriter.write(format);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewrite(List<String> orders) {
        try {
            FileWriter fileWriter = new FileWriter(ORDERS_PATH);
            for (String string : orders) {
                fileWriter.write(string);
                fileWriter.write("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            for (String string : strings) {
                String[] s = string.split(" ");
                if (Integer.parseInt(s[0]) == id) {
                    strings.remove(string);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        rewrite(strings);


    }

    @Override
    public void updateType(int id, Type type) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            int count = 0;
            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    splits[4] = type.name();
                    String s = Arrays.toString(splits);
                    strings.add(count, s);
                    break;
                }
                count++;
            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(int id, User user) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            int count = 0;
            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    splits[5] = user.getName();
                    String s = Arrays.toString(splits);
                    strings.add(count, s);
                    break;
                }
                count++;
            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void updateAddress(int id, Address address) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            int count = 0;
            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    splits[1] = address.getStreet();
                    String s = Arrays.toString(splits);
                    strings.add(count, s);
                    break;
                }
                count++;
            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void updateStore(int id, Store store) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            int count = 0;
            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    splits[2] = store.getName();
                    String s = Arrays.toString(splits);
                    strings.add(count, s);
                    break;
                }
                count++;
            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void addBook(int id, Book book) {
        List<String> strings = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            int count = 0;
            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    String[] booksSplit = splits[3].split(",");
                    List<String> booksSplit1 = List.of(booksSplit);
                    booksSplit1.add(String.valueOf(book.getId()));
                    String[] strings1 = booksSplit1.toArray(new String[0]);
                    splits[3] = Arrays.toString(strings1);
                    String s = Arrays.toString(splits);
                    strings.add(count, s);
                    break;
                }
                count++;
            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteBook(int id, Book book) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(ORDERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }

            for (String string : strings) {
                String[] splits = string.split(SPACE);
                if (Integer.parseInt(splits[ID_ORDER]) == id) {
                    String[] booksSplit = splits[3].split(",");
                    List<String> booksSplit1 = new ArrayList<>(List.of(booksSplit));
                    booksSplit1.remove(String.valueOf(book.getId()));
                }

            }
            rewrite(strings);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    @Override
    public Order getById(int id) {
        return null;
    }

    @Override
    public List<Order> getAllByUser(User user) {
        return null;
    }

    @Override
    public List<Order> getAllByAddress(Address address) {
        return null;
    }

    @Override
    public List<Order> getAllByStore(Store store) {
        return null;
    }

    @Override
    public List<Order> getAllByType(Type type) {
        return null;
    }

    @Override
    public boolean contains(int id) {
        return false;
    }
}
