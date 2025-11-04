

# Account

Data structure containing account details

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**displayNumber** | **String** | Account number to be displayed |  [optional] |
|**nickName** | **String** | NickName account Name (alias) the customer has assigned to the account for easy identification. Applies only when the operation is used by a physical or business customer, not when used by Santander staff.  |  [optional] |
|**baseCurrency** | **String** | The currency code associated to the account. Follows ISO-4217. |  [optional] |
|**status** | **Status** |  |  [optional] |
|**accountIdentifiersList** | [**List&lt;AccountIdentifiersListInner&gt;**](AccountIdentifiersListInner.md) | Account identifiers list |  [optional] |
|**customer** | [**CustomerAssociated**](CustomerAssociated.md) |  |  [optional] |
|**productId** | **String** | UUID identification type |  [optional] |
|**virtualAccountStructureId** | **String** | UUID identification type |  [optional] |
|**productName** | **String** | Name of the product associated with the account |  [optional] |
|**accounting** | [**Accounting**](Accounting.md) |  |  [optional] |
|**legalEntity** | [**LegalEntity**](LegalEntity.md) |  |  [optional] |
|**balances** | [**BalancesWithRealAccountAvailable**](BalancesWithRealAccountAvailable.md) |  |  [optional] |
|**realAccountIdentifierList** | [**List&lt;AccountIdentifiersListInner&gt;**](AccountIdentifiersListInner.md) | Account identifiers list |  [optional] |
|**openingDateTime** | **OffsetDateTime** | Balance last update date time.  The value uses the complete data format defined as isoDateTime (TimeStamp): \&quot;YYYY-MM-DD-HH:MM:SS.MS\&quot;  Where:  - YYYY: 4-digit year  - MM: 2-digit month (for example, 01 &#x3D; January)  - DD: 2-digit day of the month (01 through 31) HH &#x3D; hour, MM&#x3D;minutes, SS&#x3D;seconds, MS&#x3D;milliseconds.  |  [optional] |
|**lastUpdateDateTime** | **OffsetDateTime** | Last update date of the Account.  The value uses the complete data format defined as isoDateTime (TimeStamp): \&quot;YYYY-MM-DD-HH:MM:SS.MS\&quot;  Where:  - YYYY: 4-digit year  - MM: 2-digit month (for example, 01 &#x3D; January)  - DD: 2-digit day of the month (01 through 31) HH &#x3D; hour, MM&#x3D;minutes, SS&#x3D;seconds, MS&#x3D;milliseconds.  |  [optional] |
|**closingDateTime** | **OffsetDateTime** | Date when the Account was closed.  The value uses the complete data format defined as isoDateTime (TimeStamp): \&quot;YYYY-MM-DD-HH:MM:SS.MS\&quot;  Where:  - YYYY: 4-digit year  - MM: 2-digit month (for example, 01 &#x3D; January)  - DD: 2-digit day of the month (01 through 31) HH &#x3D; hour, MM&#x3D;minutes, SS&#x3D;seconds, MS&#x3D;milliseconds.  |  [optional] |
|**additionalInfo** | **Object** | Free JSON object. |  [optional] |
|**limits** | [**List&lt;LimitResponse&gt;**](LimitResponse.md) | Show all limits |  [optional] |



