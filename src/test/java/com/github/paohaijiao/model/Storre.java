/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.model;

import java.util.List;

/**
 * packageName com.github.paohaijiao.model
 *
 * @author Martin
 * @version 1.0.0
 * @since 2025/7/11
 */
public class Storre {
    /**
     * books : [{"title":"Book 1","author":"Author 1","price":10,"isbn":true},{"title":"Book 2","author":"Author 2","price":15,"isbn":false},{"title":"Book 3","author":"Author 3","price":20,"isbn":true}]
     * extract : {"title":"Book 3","author":"Author 3","price":20}
     */

    private ExtractBean extract;
    private List<BooksBean> books;

    public ExtractBean getExtract() {
        return extract;
    }

    public void setExtract(ExtractBean extract) {
        this.extract = extract;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class ExtractBean {
        /**
         * title : Book 3
         * author : Author 3
         * price : 20
         */

        private String title;
        private String author;
        private int price;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static class BooksBean {
        /**
         * title : Book 1
         * author : Author 1
         * price : 10
         * isbn : true
         */

        private String title;
        private String author;
        private int price;
        private boolean isbn;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public boolean isIsbn() {
            return isbn;
        }

        public void setIsbn(boolean isbn) {
            this.isbn = isbn;
        }
    }
}
