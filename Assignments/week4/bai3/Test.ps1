$ErrorActionPreference = 'Stop'
. ./Test-Helpers.ps1
javac --release 8 -d build/bai3 bai3/Solution.java
if ($LASTEXITCODE -ne 0) { throw 'Compilation failed' }
Assert-JavaOutput build/bai3 "5`n2 4 6 8 3" "2 4 6 8 8`n2 4 6 6 8`n2 4 4 6 8`n2 3 4 6 8" 'Bai 3 official sample'
Assert-JavaOutput build/bai3 "3`n2 3 1" "2 3 3`n2 2 3`n1 2 3" 'Bai 3 insert at index zero'
Assert-JavaOutput build/bai3 "3`n1 2 3" '1 2 3' 'Bai 3 already sorted'
Assert-JavaOutput build/bai3 "3`n2 2 2" '2 2 2' 'Bai 3 equal values'
Assert-JavaOutput build/bai3 "1`n9" '9' 'Bai 3 single value'
Assert-JavaOutput build/bai3 "4`n-5 -2 0 -3" "-5 -2 0 0`n-5 -2 -2 0`n-5 -3 -2 0" 'Bai 3 negative values'
