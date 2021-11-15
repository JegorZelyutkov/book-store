package com.company.storage.file;

import com.company.entity.Address;
import com.company.storage.AddressStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAddressStorage implements AddressStorage {
    private  static  final String PATH = "addresses.txt";
    private  static final int ID_INDEX = 0;
    private  static final int STREET_INDEX = 1;
    private  static  final  int HOME_INDEX = 2;
    private  static final String SPACE = " ";
    private static final String PATTERN = "%d %s %s";


    @Override
    public void save(Address address) {
        try {
            FileWriter writer = new FileWriter(PATH, true);
            String format = String.format(PATH, address.getId(), address.getStreet(), address.getHome());
            writer.write(format);
            writer.write('\n');
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAll() {
        List<Address> list = new ArrayList<>();
        try {
            FileReader reader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while((line = bufferedReader.readLine() ) != null){
                String[] lines = line.split(SPACE);
                list.add(map(lines));

            }
            return  list;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private Address map(String[] lines){
        return new Address(Integer.parseInt(lines[ID_INDEX]), lines[STREET_INDEX], lines[HOME_INDEX]);
    }

    @Override
    public Address getById(int id) {
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine() ) != null ){
                String[] lines = line.split(SPACE);
                if (Integer.parseInt(lines[ID_INDEX]) == id){
                    return map(lines);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> getAllByStreet(String street) {
        try {
            List<Address> listOfAddresses = new ArrayList<>();
            FileReader fileReader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine() ) != null){
                String[] lines = line.split(SPACE);
                if(lines[STREET_INDEX].equals(street)){
                    listOfAddresses.add(map(lines));
                }
            }
            return listOfAddresses;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> getAllByHome(int home) {
        try {
            List<Address> listOfAddresses = new ArrayList<>();
            FileReader fileReader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] lines = line.split(SPACE);
                if(Integer.parseInt(lines[HOME_INDEX]) == home){
                    listOfAddresses.add(map(lines));
                }
            }
            return  listOfAddresses;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, String street) {
        List<Address> all = getAll();
        for (Address address : all) {
            if(address.getId() == id){
                address.setStreet(street);
                break;
            }
        }
        writeList(all);

    }



    @Override
    public void updateHome(int id, String home) {
        List<Address> all = getAll();
        for (Address address : all) {
            if(address.getId() == id){
                address.setHome(home);
                break;
            }
        }
        writeList(all);

    }

    private void writeList(List<Address> all) {
        try {
            FileWriter fileWriter = new FileWriter(PATH);
            for (Address address : all) {
                fileWriter.write(String.format(PATTERN, address.getId(), address.getStreet(), address.getHome()));
                fileWriter.write("\n");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Address> all = getAll();
        for (Address address : all) {
            if(address.getId() == id){
                all.remove(address);
                break;
            }
        }
        writeList(all);
    }

    @Override
    public boolean contains(int id) {
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String s = line.split(SPACE)[ID_INDEX];
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
    public boolean contains(String street) {
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String s = line.split(SPACE)[STREET_INDEX];
                if (s.equals(street)) {
                    return true;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(Address address) {
        List<Address> all = getAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).equals(address)){
                return  true;
            }
        }

        return false;
    }
}
