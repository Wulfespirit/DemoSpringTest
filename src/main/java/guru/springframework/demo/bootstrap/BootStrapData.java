package guru.springframework.demo.bootstrap;

import guru.springframework.demo.domain.Author;
import guru.springframework.demo.domain.Book;
import guru.springframework.demo.domain.Publisher;
import guru.springframework.demo.repositories.AuthorRepository;
import guru.springframework.demo.repositories.BookRepository;
import guru.springframework.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
        private final AuthorRepository authorRepository;
        private final BookRepository bookRepository;

        private final PublisherRepository publisherRepository;

        public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
            this.authorRepository = authorRepository;
            this.bookRepository = bookRepository;
            this.publisherRepository = publisherRepository;
        }

        @Override
        public void run(String... args) throws Exception {

            System.out.println("Starting here");

            Publisher publisher = new Publisher();
            publisher.setName("Okey Dokey Publishing");
            publisher.setCity("Edmonton");
            publisher.setState("Texas");

            publisherRepository.save(publisher);

            Author eric = new Author("Eric", "Evans");
            Book ddd = new Book("Domain Driven Design", "123123");
            eric.getBooks().add(ddd);
            ddd.getAuthors().add(eric);
            ddd.setPublisher(publisher);
            publisher.getBooks().add(ddd);

            authorRepository.save(eric);
            bookRepository.save(ddd);
            publisherRepository.save(publisher);

            Author rod = new Author("Rod", "Johnson");
            Book noEJB = new Book("J2EE Development without EJB", "393939393");
            rod.getBooks().add(noEJB);
            noEJB.getAuthors().add(rod);

            noEJB.setPublisher(publisher);
            publisher.getBooks().add(noEJB);

            authorRepository.save(rod);
            bookRepository.save(noEJB);
            publisherRepository.save(publisher);

            System.out.println("Started in bootstrap");
            System.out.println("Number of books: " + bookRepository.count());
            System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
        }

}
