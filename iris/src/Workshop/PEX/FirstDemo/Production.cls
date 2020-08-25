Class Workshop.PEX.FirstDemo.Production Extends Ens.Production
{

XData ProductionDefinition
{
<Production Name="Workshop.PEX.FirstDemo.Production" TestingEnabled="true" LogGeneralTraceEvents="false">
  <Description>Una Proucción de Demo para PEX</Description>
  <ActorPoolSize>2</ActorPoolSize>
  <Setting Target="Adapter" Name="ShutdownTimeout">20</Setting>
  <Item Name="FirstOperation" Category="" ClassName="EnsLib.PEX.BusinessOperation" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayPort">55555</Setting>
    <Setting Target="Host" Name="%gatewayHost">jgw</Setting>
    <Setting Target="Host" Name="%remoteClassname">workshop.pex.FirstOperation</Setting>
    <Setting Target="Host" Name="%remoteSettings">CLIENTID=4</Setting>
  </Item>
  <Item Name="MessageRouter" Category="" ClassName="EnsLib.MsgRouter.RoutingEngine" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="false" Schedule="">
    <Setting Target="Host" Name="BusinessRuleName">Workshop.PEX.FirstDemo.MessageRouterRoutingRule</Setting>
    <Setting Target="Host" Name="ResponseFrom">FirstOperation</Setting>
  </Item>
  <Item Name="FirstService" Category="" ClassName="EnsLib.PEX.BusinessService" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="false" Schedule="">
    <Setting Target="Host" Name="%gatewayPort">55555</Setting>
    <Setting Target="Host" Name="%gatewayHost">jgw</Setting>
    <Setting Target="Host" Name="%remoteClassname">workshop.pex.FirstService</Setting>
    <Setting Target="Host" Name="%remoteSettings">TargetConfigName=FirstProcess</Setting>
  </Item>
  <Item Name="FirstProcess" Category="" ClassName="EnsLib.PEX.BusinessProcess" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="false" Schedule="">
    <Setting Target="Host" Name="%gatewayPort">55555</Setting>
    <Setting Target="Host" Name="%gatewayHost">jgw</Setting>
    <Setting Target="Host" Name="%remoteClassname">workshop.pex.FirstProcess</Setting>
    <Setting Target="Host" Name="%remoteSettings">Timeout=PT20S
TargetConfigName=FirstOperation</Setting>
  </Item>
</Production>
}

}
