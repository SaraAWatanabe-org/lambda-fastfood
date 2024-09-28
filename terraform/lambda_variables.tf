variable "region" {
  description = "Região AWS onde a função Lambda será criada"
  type        = string
}

variable "function_name" {
  description = "Nome da função Lambda"
  type        = string
}

variable "handler" {
  description = "Handler da função Lambda"
  type        = string
}

variable "client_id" {
  description = "ID do cliente do Cognito"
  type        = string
}
