package com.example.datapenduduk.model.dto;

import com.example.datapenduduk.model.entities.Kelurahan;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KependudukanData {
    @NotEmpty(message = "NIK wajib diisi")
    @Pattern(regexp = "^\\d{16}$", message = "NIK harus terdiri dari 16 digit")
    private String nik;

    @NotEmpty(message = "Nama wajib diisi")
    private String name;

    @NotEmpty(message = "Tanggal lahir wajib diisi")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String tanggalLahir;

    @NotEmpty(message = "Jenis kelamin wajib diisi")
    @Pattern(regexp = "LAKI-LAKI|PEREMPUAN", message = "Jenis kelamin harus LAKI-LAKI atau PEREMPUAN")
    private String jenisKelamin;

    @NotEmpty(message = "Agama wajib diisi")
    @Pattern(regexp = "ISLAM|PROTESTAN|KATOLIK|HINDU|BUDDHA|KHONGHUCU", message = "Agama harus ISLAM, PROTESTAN, KATOLIK, HINDU, BUDDHA, atau KHONGHUCU")
    private String agama;

    @NotEmpty(message = "Status wajib diisi")
    @Pattern(regexp = "BELUM KAWIN|KAWIN|CERAI HIDUP|CERAI MATI", message = "Status harus BELUM KAWIN, KAWIN, CERAI HIDUP, atau CERAI MATI")
    private String status;

    @NotEmpty(message = "Pekerjaan wajib diisi")
    private String pekerjaan;

    @NotEmpty(message = "Kewarganegaraan wajib diisi")
    @Pattern(regexp = "WNI|WNA", message = "Kewarganegaraan harus WNI atau WNA")
    private String kewarganegaraan;

    private Kelurahan kelurahan;
}
