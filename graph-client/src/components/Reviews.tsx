import { useParams } from "react-router-dom";
import { GetBookResult } from "./BookDetails";
import { useQuery } from "@apollo/client";
import ErrorMessage from "./ErrorMessage";
import { GET_BOOK_REVIEWS } from "../graph-queries";

export default function Reviews() {
  const { bid } = useParams();
  const { loading, error, data } = useQuery<GetBookResult>(GET_BOOK_REVIEWS, {
    variables: { bookId: bid },
  });

  const book = data === undefined ? undefined : data.getBook;

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

      <section className="reviewSection">
        <div style={{ fontWeight: "bold" }}>Reviews:</div>
        {book && book.reviews && book.reviews.length > 0 ? (
          book.reviews.map((review) => (
            <div className="grid">
              <div className="review">
                {review.review && (
                  <div className="reviewContent">{review.review}</div>
                )}
              </div>
              <div className="reviewer">
                {review.reviewerName && <div>Name: {review.reviewerName}</div>}
                {review.rating && <div>Rating: {review.rating}</div>}
              </div>
            </div>
          ))
        ) : (
          <div>No reviews available.</div>
        )}
      </section>
    </>
  );
}
