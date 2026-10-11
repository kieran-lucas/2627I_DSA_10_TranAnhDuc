function Assert-JavaOutput {
    param([string]$ClassPath, [string]$InputText, [string]$Expected, [string]$Name)
    $lines = $InputText | & java -cp $ClassPath Solution
    if ($LASTEXITCODE -ne 0) { throw "Java failed: $Name" }
    $actual = ($lines -join "`n").Replace("`r", '').TrimEnd("`n")
    $expectedText = $Expected.Replace("`r", '').TrimEnd("`n")
    if ($actual -cne $expectedText) {
        throw "Failed: $Name`nExpected:`n$expectedText`nActual:`n$actual"
    }
    Write-Output "PASS: $Name"
}
