import { gql, useMutation } from "@apollo/client";
import { useEffect, useState } from "react";
import { GET_BOOK } from "./BookDetails";
import ErrorMessage from "./ErrorMessage";

type AddReviewInputs = {
  bid: string;
  setShowAddReviewForm: (showAddReviewForm: boolean) => void;
};

const ADD_REVIEW = gql`
  mutation AddReview(
    $bid: ID!
    $reviewerName: String!
    $review: String!
    $rating: Int!
  ) {
    addReview(
      bid: $bid
      reviewInput: {
        reviewerName: $reviewerName
        review: $review
        rating: $rating
      }
    ) {
      reviews {
        review
        reviewerName
        rating
      }
    }
  }
`;
export default function AddReview(props: AddReviewInputs) {
  const { bid, setShowAddReviewForm } = props;
  const [addReview, { data, loading, error }] = useMutation(ADD_REVIEW, {
    refetchQueries: [
      GET_BOOK, // DocumentNode object parsed with gql
      "GetBookById", // Query name
    ],
  });
  const [review, setReview] = useState<string>();
  const [reviewer, setReviewer] = useState<string>();
  const [rating, setRating] = useState<number>();

  function handleOnSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (loading) return;
    addReview({
      variables: {
        bid: bid,
        reviewerName: reviewer,
        review: review,
        rating: rating,
      },
    });
    setShowAddReviewForm(false);
  }

  useEffect(() => {
    if (data) {
      console.log("reviews:" + JSON.stringify(data));
    }
  }, [data]);

  return (
    <div>
      <h4>Add Review</h4>
      {error && (
        <ErrorMessage
          message="Unable to fetch the book details."
          messageFromBackend={error.message}
        />
      )}

      <form onSubmit={(e) => handleOnSubmit(e)}>
        <label htmlFor="review">Review:</label>
        <textarea
          id="review"
          name="review"
          value={review}
          onChange={(e) => setReview(e.currentTarget.value)}
          required
          placeholder="Enter your review comments"
        ></textarea>
        <div className="grid">
          <div>
            <label htmlFor="name">Name:</label>
            <input
              id="name"
              name="name"
              type="text"
              value={reviewer}
              onChange={(e) => setReviewer(e.currentTarget.value)}
              required
              placeholder="Enter your name"
            ></input>
          </div>
          <div>
            <label htmlFor="rating">Rating:</label>
            <select
              id="rating"
              name="rating"
              required
              defaultValue={"-1"}
              value={rating}
              onChange={(e) => setRating(parseInt(e.currentTarget.value))}
            >
              <option value="-1">Select</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
        </div>

        <div className="buttonContainer">
          <button type="submit" className={loading ? "secondary" : ""}>
            Submit
          </button>
          <button
            onClick={(e) => {
              e.preventDefault();
              setShowAddReviewForm(false);
            }}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}
