import { gql } from "@apollo/client";

export const ADD_REVIEW = gql`
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
