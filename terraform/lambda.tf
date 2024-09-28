resource "aws_iam_role" "lambda_role" {
  name = "lambda_cognito_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "lambda.amazonaws.com"
        }
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "lambda_policy_attach" {
  role       = aws_iam_role.lambda_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_lambda_function" "cognito_login_function" {
  function_name = var.function_name
  role          = aws_iam_role.lambda_role.arn
  handler       = var.handler
  runtime       = "java11"

  filename    = "base_lambda.jar"
  memory_size = 512
  timeout     = 20


  environment {
    variables = {
      CLIENT_ID = var.client_id
    }
  }

  depends_on = [aws_iam_role_policy_attachment.lambda_policy_attach]
}
