/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customer;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author ASUS
 */

public class CustomerApp extends JFrame {
    private JTextField tfNama, tfAlamat, tfTelepon;
    private JButton btnSimpan;
    private CustomerDB db;

    public CustomerApp() {
        setTitle("Aplikasi Customer");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(10, 10, 100, 25);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(120, 10, 150, 25);
        add(tfNama);

        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setBounds(10, 40, 100, 25);
        add(lblAlamat);

        tfAlamat = new JTextField();
        tfAlamat.setBounds(120, 40, 150, 25);
        add(tfAlamat);

        JLabel lblTelepon = new JLabel("Telepon:");
        lblTelepon.setBounds(10, 70, 100, 25);
        add(lblTelepon);

        tfTelepon = new JTextField();
        tfTelepon.setBounds(120, 70, 150, 25);
        add(tfTelepon);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(100, 110, 100, 25);
        add(btnSimpan);

        db = new CustomerDB();

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = tfNama.getText();
                String alamat = tfAlamat.getText();
                String telepon = tfTelepon.getText();

                Customer customer = new Customer(nama, alamat, telepon);
                db.insertCustomer(customer);

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerApp();
    }
}

