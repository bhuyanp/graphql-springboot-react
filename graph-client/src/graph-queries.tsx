import { gql } from "@apollo/client";

export const GET_BOOKS = gql`
  query GetAllBooks {
    allBooks {
      bid
      title
      description
      releaseDate
    }
  }
`;

export const GET_BOOK = gql`
  query GetBookById($bookId: String) {
    getBook(bookId: $bookId) {
      bid
      title
      description
      releaseDate
      publisher {
        pid
        name
        city
        state
        country
      }
      authors {
        aid
        name
        email
      }
    }
  }
`;

export const GET_BOOK_REVIEWS = gql`
  query GetBookById($bookId: String) {
    getBook(bookId: $bookId) {
      bid
      reviews {
        rid
        reviewerName
        review
        rating
      }
    }
  }
`;
