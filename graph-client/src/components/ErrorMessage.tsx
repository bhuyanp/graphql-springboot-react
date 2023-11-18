export default function ErrorMessage(props: {
  message: string;
  messageFromBackend: string;
}) {
  return (
    <blockquote className="error">
      {props.message}
      <div>Message: {props.messageFromBackend}</div>
    </blockquote>
  );
}
