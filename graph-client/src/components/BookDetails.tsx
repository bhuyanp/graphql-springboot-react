import { useQuery } from "@apollo/client";
import { useParams } from "react-router-dom";
import { Book } from "./BookList";
import { useState } from "react";
import AddReview from "./AddReview";
import ErrorMessage from "./ErrorMessage";
import Reviews from "./Reviews";
import { GET_BOOK } from "../graph-queries";
export type GetBookResult = {
  getBook: Book;
};

export default function BookDetails() {
  const { bid } = useParams();
  const [showReviews, setShowReviews] = useState<boolean>(false);
  const [showAddReviewForm, setShowAddReviewForm] = useState<boolean>(false);
  const { loading, error, data } = useQuery<GetBookResult>(GET_BOOK, {
    variables: { bookId: bid },
  });
  const book = data === undefined ? undefined : data.getBook;
  console.log(data);

  return (
    <div className="container">
      {loading && (
        <div aria-busy="true" className="loading">
          Loading...
        </div>
      )}
      {error && (
        <ErrorMessage
          message="Unable to fetch the book details."
          messageFromBackend={error.message}
        />
      )}
      {book && (
        <article className="bookDetails">
          <section className="mainSection">
            <h3>{book.title}</h3>
            <div>{book.description}</div>
            <div className="releaseDate">
              <span>Release Date: </span>
              {new Date(book.releaseDate).toDateString()}
            </div>
          </section>
          <div className="grid">
            {book.authors && book.authors.length > 0 && (
              <section className="authorSection">
                <div style={{ fontWeight: "bold" }}>Authors:</div>
                {book.authors.map((author) => (
                  <div className="author" key={author.aid}>
                    {author.name && <div>Name: {author.name}</div>}
                    {author.email && <div>Email: {author.email}</div>}
                  </div>
                ))}
              </section>
            )}
            {book.publisher && book.publisher.pid && (
              <section className="publisherSection">
                <div style={{ fontWeight: "bold" }}>Publisher:</div>
                <div>{book.publisher.name}</div>
                {book.publisher.city && <div>City: {book.publisher.city}</div>}
                {book.publisher.state && (
                  <div>State: {book.publisher.state}</div>
                )}
                {book.publisher.country && (
                  <div>Country: {book.publisher.country}</div>
                )}
              </section>
            )}
          </div>

          {showReviews && <Reviews />}

          {!showAddReviewForm && (
            <div className="buttonContainer">
              <a
                role="button"
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  setShowReviews(!showReviews);
                }}
              >
                {!showReviews ? "Show Reviews" : "Hide Reviews"}
              </a>
              <a
                role="button"
                href="#"
                onClick={(e) => {
                  e.preventDefault();
                  setShowAddReviewForm(!showAddReviewForm);
                }}
              >
                Add Review
              </a>
            </div>
          )}
          {showAddReviewForm && (
            <AddReview
              bid={book.bid}
              setShowAddReviewForm={setShowAddReviewForm}
            />
          )}
        </article>
      )}
    </div>
  );
}
