package com.project.khob.config;

import com.project.khob.domain.entities.*;
import com.project.khob.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Runner  implements ApplicationRunner {

    // This class runs at the startup of the application in order to populate the database with starting data

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;
    private  final VariantRepository variantRepository;
    @Override
    public void run(ApplicationArguments args) {
        User adminUser = User.builder()
                .email("imennaija17@gmail.com")
                .firstName("Imen")
                .lastName("Naija")
                .password(passwordEncoder.encode("a"))
                .roles(new HashSet<>(List.of(UserRole.ADMIN, UserRole.USER)))
                .build();
        User regularUser = User.builder()
                .email("amaninaija@gmail.com")
                .firstName("Amani")
                .lastName("Naija")
                .password(passwordEncoder.encode("b"))
                .build();
        userRepository.saveAll(List.of(adminUser,regularUser));

        Category categoryA = Category.builder().name("Top").build();
        Category categoryB = Category.builder().name("Accessory").build();
        categoryRepository.saveAll(List.of(categoryA,  categoryB));

        SubCategory subCategoryA = SubCategory.builder().name("Cardigan").category(categoryA).build();
        SubCategory subCategoryB = SubCategory.builder().name("Bra").category(categoryA).build();
        SubCategory subCategoryC = SubCategory.builder().name("Handbag").category(categoryB).build();
        SubCategory subCategoryD = SubCategory.builder().name("Phone case").category(categoryB).build();
        SubCategory subCategoryE = SubCategory.builder().name("Earrings").category(categoryB).build();
        subCategoryRepository.saveAll(List.of(subCategoryA, subCategoryB, subCategoryC, subCategoryD,subCategoryE));

        Photo photoA = Photo.builder().url("https://i.ibb.co/CBG1ZTf/img-4.jpg").build();
        Photo photoB = Photo.builder().url("https://i.ibb.co/CBG1ZTf/img-5.jpg").build();
        Photo photoC = Photo.builder().url("https://i.ibb.co/CBG1ZTf/img-6.jpg").build();
        Photo photoD = Photo.builder().url("https://i.ibb.co/CBG1ZTf/img-7.jpg").build();
        Photo photoE = Photo.builder().url("https://i.ibb.co/CBG1ZTf/img-8.jpg").build();
//        photoRepository.saveAll(List.of(photoA,photoB,photoC,photoD,photoE));

        Product productA = Product.builder().name("Earrings A").price(100).photos(List.of(photoA)).subCategory(subCategoryE).build();
        Product productB = Product.builder().name("Handbag A").price(100).photos(List.of(photoB)).subCategory(subCategoryC).build();
        Product productC = Product.builder().name("Bra A").price(100).photos(List.of(photoC)).subCategory(subCategoryB).build();
        Product productD = Product.builder().name("Handbag B").price(100).photos(List.of(photoD)).subCategory(subCategoryC).build();
        Product productE = Product.builder().name("Bra B").price(100).photos(List.of(photoE)).subCategory(subCategoryB).build();
        productRepository.saveAll(List.of(productA,productB,productC,productD,productE));

        Variant variantA = Variant.builder().color("Blue").size("XL").product(productC).build();
        Variant variantB = Variant.builder().color("Red").size("L").product(productC).build();
        variantRepository.save(variantA);
        variantRepository.save(variantB);
    }
}
