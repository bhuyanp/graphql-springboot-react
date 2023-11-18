import { useQuery, gql } from "@apollo/client";
import ErrorMessage from "./ErrorMessage";

export type Author = {
  aid: number;
  name: string;
  email: string;
};
export type Publisher = {
  pid: number;
  name: string;
  city: string;
  state: string;
  country: string;
};

export type Review = {
  rid: number;
  reviewerName: string;
  review: string;
  rating: number;
};

export type Book = {
  bid: string;
  title: string;
  description: string;
  releaseDate: string;
  authors: Author[];
  publisher: Publisher;
  reviews?: Review[];
};

type Result = {
  allBooks: Book[];
};

const GET_BOOKS = gql`
  query GetAllBooks {
    allBooks {
      bid
      title
      description
      releaseDate
    }
  }
`;

function BookList() {
  const { loading, error, data } = useQuery<Result>(GET_BOOKS);
  return (
    <>
      {loading && (
        <div aria-busy="true" className="loading">
          Loading...
        </div>
      )}
      {error && (
        <ErrorMessage
          message="Unable to fetch the book list."
          messageFromBackend={error.message}
        />
      )}
      <div className="bookContainer">
        {data &&
          data.allBooks &&
          (data.allBooks.length > 0 ? (
            data.allBooks.map((book) => (
              <article
                key={book.bid}
                className="book"
                onClick={() => (window.location.href = "/#/book/" + book.bid)}
              >
                <div className="title">{book.title}</div>
                <div className="description">{book.description}</div>
                <div className="releaseDate">
                  <span>Release Date: </span>
                  {new Date(book.releaseDate).toDateString()}
                </div>
              </article>
            ))
          ) : (
            <>
              <div>No books available in the store</div>
            </>
          ))}
      </div>
    </>
  );
}

export default BookList;
