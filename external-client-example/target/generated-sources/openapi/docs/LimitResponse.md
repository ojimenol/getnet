

# LimitResponse

Show all limits

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**limitId** | **String** | UUID identification type |  |
|**limitCurrency** | **String** | The currency code associated to the account. Follows ISO-4217. |  |
|**limitAmount** | **String** | Limit amount |  |
|**limitType** | [**LimitTypeEnum**](#LimitTypeEnum) | indicates the type of limit |  |



## Enum: LimitTypeEnum

| Name | Value |
|---- | -----|
| OVERDRAFT | &quot;overdraft&quot; |
| INTRA_DAY_LIMIT | &quot;intra_day_limit&quot; |
| PENCIL_LIMIT | &quot;pencil_limit&quot; |
| BALANCE_LIMIT | &quot;balance_limit&quot; |



