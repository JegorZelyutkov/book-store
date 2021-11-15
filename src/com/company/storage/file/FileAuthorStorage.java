package com.company.storage.file;

import com.company.entity.Author;
import com.company.storage.AuthorStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAuthorStorage implements AuthorStorage {

    private static final String PATTERN = "%d %s %s";
    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final String PATH = "authors.txt";
    private static final String REGEXP = " ";

    public static void main(String[] args) {
        FileAuthorStorage dbAuthorStorage = new FileAuthorStorage();
//dbAuthorStorage.save(new Author(1, "Test", "Test"));
//        dbAuthorStorage.delete(2);
        dbAuthorStorage.updateName(1, "new");

    }

    @Override
    public void save(Author author) {
        try {
            FileWriter fileWriter = new FileWriter(PATH, true);
            String format = String.format(PATTERN, incId(), author.getName(), author.getDescription());
            fileWriter.write(format);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int incId() {

        return 0;
    }

    @Override
    public void delete(int id) {
        List<Author> listOfAuthors = getAll();
        for (Author listOfAuthor : listOfAuthors) {
            if (listOfAuthor.getId()==id){
                listOfAuthors.remove(listOfAuthor);
                break;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Author listOfAuthor : listOfAuthors) {
                fileWriter.write(String.format(PATTERN, listOfAuthor.getId(), listOfAuthor.getName(), listOfAuthor.getDescription()));
                fileWriter.write("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Author author) {
        List<Author> listOfAuthors = getAll();
        listOfAuthors.remove(author);
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Author listOfAuthor : listOfAuthors) {
                fileWriter.write(String.format(PATTERN, listOfAuthor.getId(), listOfAuthor.getName(), listOfAuthor.getDescription()));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateName(int id, String name) {
        List<Author> listOfAuthors = getAll();

        for (Author listOfAuthor : listOfAuthors) {
            if(listOfAuthor.getId() == id){
                listOfAuthor.setName(name);
                break;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Author listOfAuthor : listOfAuthors) {
                fileWriter.write(String.format(PATTERN, listOfAuthor.getId(), listOfAuthor.getName(), listOfAuthor.getDescription()));
                fileWriter.write("\n");
            }
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateDescription(int id, String description) {
        List<Author> listOfAuthors = getAll();

        for (Author listOfAuthor : listOfAuthors) {
            if(listOfAuthor.getId() == id){
                listOfAuthor.setDescription(description);
                break;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Author listOfAuthor : listOfAuthors) {
                fileWriter.write(String.format(PATTERN, listOfAuthor.getId(), listOfAuthor.getName(), listOfAuthor.getDescription()));
                fileWriter.write("\n");
            }
            fileWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean exist(int id) {
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String s = line.split(REGEXP)[ID_INDEX];
                if (Integer.parseInt(s) == id) {
                    return true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean exist(String name) {
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(REGEXP)[NAME_INDEX].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Author> getAll() {
        List<Author> listOfAuthors = new ArrayList<>();
        try {
            FileReader reader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(REGEXP);
                listOfAuthors.add(new Author(Integer.parseInt(lines[ID_INDEX]), lines[NAME_INDEX], lines[DESCRIPTION_INDEX]));
            }
            return listOfAuthors;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Author getById(int id) {
        List<Author> authorsList = getAll();
        for (Author all : authorsList) {
            if (all.getId() == id) {
                return all;
            }
        }
        return null;
    }

    @Override
    public Author getByName(String name) {
        List<Author> authorList = getAll();
        for (Author all : authorList) {
            if (all.getName().equals(name)) {
                return all;
            }
        }
        return null;
    }
}
