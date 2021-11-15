package com.company.storage.file;

import com.company.entity.Address;
import com.company.entity.Store;
import com.company.storage.StoreStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStoreStorage implements StoreStorage {
    private static final String PATTERN = "%d %s %d";
    private static final String STORES_PATH = "stores.txt";
    private static final String ADDRESSES_PATH = "addresses.txt";

    private static final String SPACE = " ";
    private static final int ID_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int ADDRESS_ID = 2;


    @Override
    public void save(Store store) {
        try {
            FileWriter fileWriter = new FileWriter(STORES_PATH, true);
            String format = String.format(PATTERN, store.getId(), store.getName(), store.getAddress().getId());
            fileWriter.write(format);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Store> listOfStores = getAll();
        for (Store all : listOfStores) {
            if (all.getId() == id) {
                listOfStores.remove(all);
                break;
            }
        }
        try {
            FileWriter writer = new FileWriter(STORES_PATH);
            for (Store all : listOfStores) {
                writer.write(String.format(PATTERN, ID_INDEX, TITLE_INDEX, all.getAddress().getId()));
                writer.write("\n");
                writer.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, String name) {
        List<Store> all = getAll();
        for (Store store : all) {
            if (store.getId() == id) {
                store.setName(name);
                break;
            }
        }
        try {
            FileWriter writer = new FileWriter(STORES_PATH);
            for (Store store : all) {
                writer.write(String.format(PATTERN, store.getId(), store.getName(), store.getAddress().getId()));
                writer.write("\n");
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(int id, Address address) {
        List<Store> all = getAll();
        for (Store store : all) {
            if (store.getId() == id) {
                store.setAddress(address);
                break;
            }
        }
        try {
            FileWriter writer = new FileWriter(STORES_PATH);
            for (Store store : all) {
                int idOfAddress = store.getAddress().getId();
                writer.write(String.format(PATTERN, store.getId(), store.getName(), idOfAddress));
                writer.write("\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Store> getAll() {
        List<Store> listOfAllStores = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(STORES_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(SPACE);

                listOfAllStores.add(new Store(Integer.parseInt(splits[ID_INDEX]),
                        getAddressById(Integer.parseInt(splits[1])), (splits[TITLE_INDEX])));
                return listOfAllStores;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Address getAddressById(int id) {
        try {
            FileReader fileReaderForAuthors = new FileReader("addresses.txt");
            BufferedReader readerForAuthors = new BufferedReader(fileReaderForAuthors);
            String lineForAddresses;

            while ((lineForAddresses = readerForAuthors.readLine()) != null) {
                String[] splitsForAddresses = lineForAddresses.split(SPACE);
                if ((Integer.parseInt(splitsForAddresses[0])) == id) {
                    return new Address(id, splitsForAddresses[1], splitsForAddresses[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        List<Store> storeList = getAll();
        for (Store all : storeList) {
            if (all.getId() == id) {
                return all;
            }
        }
        return null;
    }


    @Override
    public boolean contains(int id) {
        try {
            FileReader fileReader = new FileReader(STORES_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(SPACE);
                if ((Integer.parseInt(splits[ID_INDEX])) == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean contains(Store store) {
        List<Store> all = getAll();
        for (Store value : all) {
            if (value.equals(store)) {
                return true;
            }
        }

        return false;
    }
}
